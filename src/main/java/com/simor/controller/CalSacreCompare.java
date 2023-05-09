
package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSacreCompare {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private ComparadorModel comRec, comEnv = null;

	public CalSacreCompare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();

		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
		this.comEnv = this.listaComparadorModel();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		calculoModel.setJuroInicial(this.dashboardModel.getTaxa());
	}

	public ComparadorModel sys_sacre() {
		return this.comEnv;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		//PRIMEIROMCALCULO
		aux.adicionaDoubleAnyArray(0, this.dashboardModel.getValorEmprestFinancia() / this.dashboardModel.getPrazo());
		//PRIMEIROMCALCULO
		aux.adicionaDoubleAnyArray(1, aux.getDoubleAnyArray()[0] + (this.getTaxaSacreCalculado() * this.dashboardModel.getValorEmprestFinancia()));
		calculoModel.setPrestacao(aux.getDoubleAnyArray()[1]);
		return calculoModel.getPrestacao();
	}
	
	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaSacreCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ComparadorModel listaComparadorModel() {
		comRec = new ComparadorModel();
		comRec.setSistema("SACRE");
		comRec.setTotalPago(this.dashboardModel.getValorEmprestFinancia());
		
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setJuroInicial(this.getTaxaSacreCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setAmortizacao(this.getCalculoDePrestacao() - calculoModel.getJuro());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				
				calculo.setPrestacao(this.getCalculoDePrestacao());
				calculo.setJuro(calculoModel.getJuro());
				/*---------------------------------*/
				comRec.setTotalJuro(comRec.getTotalJuro() + calculo.getJuro());
				comRec.setPrimeiraParcela(calculo.getPrestacao());
				comRec.setUltimaParcela(calculo.getPrestacao());
				/*---------------------------------*/
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				calculo.setDataVencimento(SistemaController.listaDataVencimento(
						SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
						this.dashboardModel.getPrazo()).get(i).getDataVencimento());

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

