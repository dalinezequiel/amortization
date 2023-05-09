package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalMajsCompare {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private ComparadorModel comRec, comEnv, comReverse = null;

	public CalMajsCompare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();

		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
		this.comEnv = this.listaComparadorModel();
	}

	public ComparadorModel sys_majs() {
		this.comReverse=new ComparadorModel();
		this.comReverse.setSistema(this.comEnv.getSistema());
		this.comReverse.setPrimeiraParcela(this.comEnv.getUltimaParcela());
		this.comReverse.setUltimaParcela(this.comEnv.getPrimeiraParcela());
		this.comReverse.setTotalJuro(this.comEnv.getTotalJuro());
		this.comReverse.setTotalPago(this.comEnv.getTotalPago());
		return this.comReverse;
	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaSacCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ComparadorModel listaComparadorModel() {
		comRec = new ComparadorModel();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		
		comRec.setSistema("MAJS/Hamburguês");
		comRec.setTotalPago(this.dashboardModel.getValorEmprestFinancia());
		
		if (this.dashboardModel != null) {
			calculoModel.setJuroInicial(this.getTaxaSacCalculado());
			calculoModel.setAmortizacao(calculoModel.getValorEmprestFinac() / this.dashboardModel.getPrazo());
			
			comRec.setPrimeiraParcela(calculoModel.getAmortizacao() + (calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac()));
			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
				calculo.setJuro(calculoModel.getJuro());
				/*---------------------------------*/
				comRec.setTotalJuro(comRec.getTotalJuro() + calculo.getJuro());
				comRec.setUltimaParcela(calculo.getPrestacao());
				/*---------------------------------*/
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
			}
			comRec.setTotalPago(comRec.getTotalPago() + comRec.getTotalJuro());
		}
		return comRec;
	}

	// EVENTO CLICK
	protected boolean calcular_click() throws ServletException, IOException {
		if (this.request.getParameter("calcular") != null) {
			return true;
		}
		return false;
	}

	// PEGAR VALORES INFORMADOS NA PAGINA
	public DashboardModel getDashboardModel() throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		if (this.calcular_click()) {
			dashboardModel.setValorEmprestFinancia(Double.parseDouble(SistemaController
					.clean(SistemaController.isNullOrEmpty(this.request.getParameter("emprest_financia").trim()))));

			dashboardModel.setTaxa(Double.parseDouble(SistemaController
					.cleanTax(SistemaController.isNullOrEmpty(this.request.getParameter("taxa").trim()))));

			dashboardModel.setPrazo(
					Integer.parseInt(SistemaController.isNullOrEmpty(this.request.getParameter("prazo").trim())));
			dashboardModel.setTaxaMensal(0);
			dashboardModel.setTaxaAnual(0);
			dashboardModel.setDataContratacao(Date.valueOf(LocalDate.now()));
			dashboardModel.setDataPrimeiraParcela(Date.valueOf(LocalDate.now()));
			dashboardModel.setTipoBalao("");
			dashboardModel.setCalcularAtraso("");
			dashboardModel.setMulta(0);
			dashboardModel.setJuroAtraso(0);
			dashboardModel.setPeriodicidadeBalao(0);
			dashboardModel.setQuantBalao(0);
			dashboardModel.setValorBalao(0);

		}
		return dashboardModel;
	}
}
