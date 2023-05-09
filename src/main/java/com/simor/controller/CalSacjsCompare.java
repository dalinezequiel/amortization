
package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSacjsCompare {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private ComparadorModel comRec, comEnv = null;
	
	private Auxilio aux = null;

	public CalSacjsCompare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
		this.comEnv = this.listaComparadorModel();
		
		aux = new Auxilio();
//		this.dashboardModel = new App(request, response).getDashboardModel();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		calculoModel.setJuroInicial(this.dashboardModel.getTaxa());
//		System.out.println("SACJS: "+this.comRec.getPrimeiraParcela());
//		System.out.println("SACJS: "+this.comRec.getUltimaParcela());
//		System.out.println("SACJS: "+this.comRec.getTotalJuro());
//		System.out.println("SACJS: "+this.comRec.getTotalPago());
	}

	public CalSacjsCompare() {
		super();
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
	}

	public ComparadorModel sys_sacjs() {
		return this.comEnv;
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

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaSacjsCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ComparadorModel listaComparadorModel() {
		comRec = new ComparadorModel();
		comRec.setSistema("SAC-JS");
		comRec.setTotalPago(this.dashboardModel.getValorEmprestFinancia());
		comRec.setPrimeiraParcela(this.getCalculoDePrestacao()+(this.getIndicePonderacao() * (dashboardModel.getPrazo())));
		
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

