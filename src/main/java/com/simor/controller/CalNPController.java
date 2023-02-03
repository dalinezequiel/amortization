package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalNPController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaNP = null;

	public CalNPController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = getCalculoModelData(request, response);
	}

	public CalNPController() {
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
		aux = new Auxilio(4);
		aux.setIntAux(1);
		//aux.setDoubleAux(3);
		
		dashboardModel.setValorEmprestFinancia(50000);
		dashboardModel.setPrazo(12);
		dashboardModel.setTaxa(1.0);
		
		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, Math.pow((aux.getIntAux() + this.getTaxaNPCalculado()), dashboardModel.getPrazo()) * this.getTaxaNPCalculado());

		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow((aux.getIntAux() + this.getTaxaNPCalculado()), dashboardModel.getPrazo()) - aux.getIntAux());

		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[0] / aux.getDoubleAnyArray()[1]);
		
		//QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, dashboardModel.getValorEmprestFinancia() * aux.getDoubleAnyArray()[2]);
		
		calculoModel.setPrestacao(aux.getDoubleAnyArray()[3]);
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
	public double getTaxaNPCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalNPModel() {
		listaNP = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao());
			calculoModel.setJuroInicial(this.getTaxaNPCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getPrestacao());
				calculo.setJuro(calculoModel.getJuro());
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				calculo.setDataVencimento(calculoModel.getDataVencimento());
				aux = new Auxilio();
				aux.setDoubleAux(0.00001);
				aux.setIntAux(0);
				calculo.setAuxilio(aux);
				listaNP.add(calculo);
			}
		}
		return listaNP;
	}
	
	public static void main (String [] args) {
		System.out.print("Testa: ");
		System.out.println(new CalNPController().getCalculoDePrestacao());
		System.out.println(new DecimalFormat("###,###.00").format(new CalNPController().getCalculoDePrestacao()));
	}
}
