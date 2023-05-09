package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import com.simor.model.CalculoModel;
import com.simor.model.ComparadorModel;
import com.simor.model.DashboardModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSalCompare {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private SalController salcon = null;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private ComparadorModel comRec, comEnv = null;

	public CalSalCompare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
		salcon = new SalController((float) this.getTaxaSalCalculado(), this.dashboardModel.getPrazo());
		this.comEnv = this.listaComparadorModel();
	}

	public CalSalCompare() {
		dashboardModel = new DashboardModel();
		this.dashboardModel.setPrazo(2);
		this.dashboardModel.setTaxa(1);
		calculoModel = new CalculoModel();
		salcon = new SalController((float) this.getTaxaSalCalculado(), this.dashboardModel.getPrazo());
	}

	public ComparadorModel sys_sal() {
		return this.comEnv;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao(double valor) {
		return this.salcon.factor() * valor;
	}

	// CALCULAR JUROS
	public double getCalculoJurosSal(int mes, double valor) {
		return this.getCalculoDePrestacao(valor) - this.getCalculoAmortizacao(mes, valor);
	}

	// CALCULAR AMORTIZACAO
	public double getCalculoAmortizacao(int mes, double valor) {
		return this.getCalculoDePrestacao(valor) / (1 + mes * this.getTaxaSalCalculado());
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ComparadorModel listaComparadorModel() {
		comRec = new ComparadorModel();
		comRec.setSistema("SAL");
		comRec.setTotalPago(this.dashboardModel.getValorEmprestFinancia());

		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao(calculoModel.getValorEmprestFinac()));

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(this.getCalculoJurosSal((i + 1), this.dashboardModel.getValorEmprestFinancia()));
				calculoModel
						.setAmortizacao(getCalculoAmortizacao((i + 1), this.dashboardModel.getValorEmprestFinancia()));
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

	// CALCULAR PRECENTUAL
	public double getTaxaSalCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	public ArrayList<Double> initial() {
		return this.salcon.initial();
	}

	public double sum() {
		return this.salcon.sum();
	}

	public double division() {
		return this.salcon.division();
	}

	public double factor() {
		return this.salcon.factor();
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
