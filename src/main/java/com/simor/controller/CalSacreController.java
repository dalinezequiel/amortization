package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSacreController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaSacre = null;

	public CalSacreController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = getCalculoModelData(request, response);
	}

	public CalSacreController() {
		super();
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRESTAÇÃO
//	public double getCalculoDePrestacao() {
//		aux = new Auxilio(4);
//		aux.setIntAux(2);
//		aux.setDoubleAux(3);
//		
//		// PRIMEIRO CALCÚLO
//		aux.adicionaDoubleAnyArray(0,
//				(dashboardModel.getValorEmprestFinancia() / dashboardModel.getPrazo()) + (this.getTaxaSacCalculado() * dashboardModel.getValorEmprestFinancia()));
//
//		calculoModel.setPrestacao(aux.getDoubleAnyArray()[0]);
//		return calculoModel.getPrestacao();
//	}

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
	public double getTaxaSacreCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalSacreModel() {
		listaSacre = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setJuroInicial(this.getTaxaSacreCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
			calculoModel.setAmortizacao(calculoModel.getValorEmprestFinac() / this.dashboardModel.getPrazo());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
				calculo.setJuro(calculoModel.getJuro());
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				calculo.setDataVencimento(calculoModel.getDataVencimento());
				
				aux = new Auxilio();
				aux.setDoubleAux(0.01);
				calculo.setAuxilio(aux);
				listaSacre.add(calculo);
			}
		}
		return listaSacre;
	}
}
