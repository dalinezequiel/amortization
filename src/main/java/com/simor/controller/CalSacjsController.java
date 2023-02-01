package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSacjsController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaSacjs = null;

	public CalSacjsController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = getCalculoModelData(request, response);
	}

	public CalSacjsController() {
		super();
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(1);//5
		aux.setIntAux(2);
		aux.setDoubleAux(3);

		// PRIMEIRO CALCÚLO
		calculoModel.setPrestacao(dashboardModel.getValorEmprestFinancia() / dashboardModel.getPrazo());
		return calculoModel.getPrestacao();
	}
	
	//INDICE DE PONDERACÃO
	public double getIndicePonderacao() {
		aux = new Auxilio(3);//5
		aux.setIntAux(2);
		aux.setDoubleAux(3);
		
		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, dashboardModel.getValorEmprestFinancia() * this.getTaxaSacjsCalculado() * aux.getDoubleAux());
		
		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, (aux.getIntAux() * dashboardModel.getPrazo() * this.getTaxaSacjsCalculado()) - (aux.getIntAux() * this.getTaxaSacjsCalculado()));
		
		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, (aux.getDoubleAnyArray()[1] + aux.getDoubleAux()) * dashboardModel.getPrazo());
		
		calculoModel.setPrestacao(aux.getDoubleAnyArray()[0] / aux.getDoubleAnyArray()[2]);
		return calculoModel.getPrestacao();
	}

	// PEGAR VALORES INFORMADOS NA PAGINA
	private DashboardModel getCalculoModelData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.dashboardModel.setValorEmprestFinancia(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("emprest_financia").trim())));
		calculoModel.setValorEmprestFinac(dashboardModel.getValorEmprestFinancia());
		this.dashboardModel
				.setTaxa(Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa").trim())));
		calculoModel.setJuroInicial(dashboardModel.getTaxa());
		this.dashboardModel.setTaxaMensal(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_mensal").trim())));
		this.dashboardModel.setTaxaAnual(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_anual").trim())));
		this.dashboardModel
				.setPrazo(Integer.parseInt(SistemaController.isNullOrEmpty(request.getParameter("prazo").trim())));
		this.dashboardModel.setDataPrimeiraParcela(Date.valueOf(request.getParameter("ultima_parcela").trim()));
		return this.dashboardModel;
	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaSacjsCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalSacjsModel() {
		listaSacjs = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao());
			calculoModel.setAmortizacao(calculoModel.getValorEmprestFinac() / dashboardModel.getPrazo());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(this.getIndicePonderacao() * (dashboardModel.getPrazo() - i));
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
				calculo.setJuro(calculoModel.getJuro());
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				calculo.setDataVencimento(calculoModel.getDataVencimento());
				aux = new Auxilio();
				aux.setDoubleAux(0.01);
				calculo.setAuxilio(aux);
				listaSacjs.add(calculo);
			}
		}
		return listaSacjs;
	}
	
	public static void main (String [] args) {
		System.out.println("TestaA: " + new CalSacjsController().getCalculoDePrestacao()*12);
	}
}
