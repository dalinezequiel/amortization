package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalGausCompare {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private ComparadorModel comRec, comEnv = null;

	public CalGausCompare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		this.request = request;
		this.response = response;
		aux = new Auxilio();
		this.dashboardModel = this.getDashboardModel();
		this.comEnv = this.listaComparadorModel();
	}

	public ComparadorModel sys_gaus() {
		return this.comEnv;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		aux.setIntAux(1);
		aux.setDoubleAux(2);
		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0,
				dashboardModel.getValorEmprestFinancia() * (this.getTaxaGausCalculado() * dashboardModel.getPrazo())
						+ dashboardModel.getValorEmprestFinancia());
		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1,
				((this.getTaxaGausCalculado() * (dashboardModel.getPrazo() - aux.getIntAux()) / aux.getDoubleAux())
						+ aux.getIntAux()) * dashboardModel.getPrazo());
		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, (aux.getDoubleAnyArray()[0] / aux.getDoubleAnyArray()[1]));
		calculoModel.setPrestacao(aux.getDoubleAnyArray()[2]);
		return calculoModel.getPrestacao();
	}

	// CALCULAR PRECENTUAL
	public double getTaxaGausCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	public double getCalculoJurosGaus(int index, double v) {
		aux = new Auxilio(4);
		aux.setIntAux(1);
		aux.setDoubleAux(2);
		dashboardModel.setValorEmprestFinancia(v);
		// CALCULO DO INDICE PONDERADO
		aux.adicionaDoubleAnyArray(0, (calculoModel.getPrestacao() * (dashboardModel.getPrazo() - index))
				- dashboardModel.getValorEmprestFinancia());
		aux.adicionaDoubleAnyArray(1, (aux.getIntAux() + (dashboardModel.getPrazo() - index))
				* ((dashboardModel.getPrazo() - index) / aux.getDoubleAux()));
		aux.adicionaDoubleAnyArray(2, (aux.getDoubleAnyArray()[0] / aux.getDoubleAnyArray()[1]));
		return aux.getDoubleAnyArray()[2] * (dashboardModel.getPrazo() - index);
	}

	public ComparadorModel listaComparadorModel() {
		calculoModel = new CalculoModel();
		comRec = new ComparadorModel();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao());
			comRec.setSistema("Gaus");
			comRec.setTotalPago(comRec.getTotalPago() + calculoModel.getValorEmprestFinac());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(this.getCalculoJurosGaus(i, calculoModel.getValorEmprestFinac()));
				calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getPrestacao());
				calculo.setJuro(calculoModel.getJuro());
				/*---------------------------------*/
				comRec.setTotalJuro(comRec.getTotalJuro() + calculo.getJuro());
				comRec.setPrimeiraParcela(calculo.getPrestacao());
				comRec.setUltimaParcela(calculo.getPrestacao());
				/*---------------------------------*/
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
