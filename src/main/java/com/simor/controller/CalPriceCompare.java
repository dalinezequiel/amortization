
package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.simor.model.*;

public class CalPriceCompare {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private Auxilio aux = null;
	private ComparadorModel comRec, comEnv = null;

	public CalPriceCompare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		this.request = request;
		this.response = response;
		aux = new Auxilio();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		calculoModel.setJuroInicial(this.dashboardModel.getTaxa());
		this.dashboardModel = this.getDashboardModel();
		this.comEnv = this.listaComparadorModel();
	}

	public ComparadorModel sys_price() {
		return this.comEnv;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		aux.setIntAux(1);
		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux()
				+ getPercentualPriceCalculado(dashboardModel.getValorEmprestFinancia(), dashboardModel.getTaxa()));
		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], dashboardModel.getPrazo()));
		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[1]
				* getPercentualPriceCalculado(dashboardModel.getValorEmprestFinancia(), dashboardModel.getTaxa())
				/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));
		// QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, dashboardModel.getValorEmprestFinancia() * aux.getDoubleAnyArray()[2]);
		calculoModel.setPrestacao(aux.getDoubleAnyArray()[3]);
		return calculoModel.getPrestacao();
	}

	// CALCULAR PRECENTUAL
	private double getPercentualPriceCalculado(double valorEmprestFinanc, double juroInicial) {
		return juroInicial / 100;
	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaPriceCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ComparadorModel listaComparadorModel() {
		comRec = new ComparadorModel();
		comRec.setSistema("PRICE");
		comRec.setTotalPago(this.dashboardModel.getValorEmprestFinancia());

		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao());
			calculoModel.setJuroInicial(this.getTaxaPriceCalculado());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getPrestacao());
				calculo.setJuro(calculoModel.getJuro());
				/*---------------------------------*/
				comRec.setTotalJuro(comRec.getTotalJuro() + calculo.getJuro());
				comRec.setPrimeiraParcela(calculo.getPrestacao());
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
