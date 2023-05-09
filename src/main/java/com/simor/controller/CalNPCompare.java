
package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalNPCompare {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private ComparadorModel comRec, comEnv = null;
	private int percentagem;
	private SerieNPController serieNP = null;

	public CalNPCompare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dashboardModel = new DashboardModel();

		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
		this.comEnv = this.listaComparadorModel();
		this.percentagem = 100;
	}

	public ComparadorModel sys_np() {
		return this.comEnv;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao(double valor) {
		this.serieNP = new SerieNPController(this.getTaxaNPCalculado());
		return this.serieNP.coeficiente() * valor;
	}

	// CALCULAR JUROS
	public double getCalculoJurosNP(int mes, double valor) {
		return (serieNP.taxaMensal(mes) / percentagem) * valor;
	}

	public ComparadorModel listaComparadorModel() {
		calculoModel = new CalculoModel();
		comRec = new ComparadorModel();
		comRec.setSistema("N. Periódicas");
		comRec.setTotalPago(this.dashboardModel.getValorEmprestFinancia());

		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao(calculoModel.getValorEmprestFinac()));
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(
						(this.serieNP.taxaMensal(i + 1) / 100) * calculoModel.getValorEmprestFinac());
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

	// CALCULAR PRECENTUAL
	public double getTaxaNPCalculado() {
		// this.dashboardModel.setTaxa(1);
		return this.dashboardModel.getTaxa() / 100;
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
