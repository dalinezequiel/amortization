package com.simor.controller;

import com.simor.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class App {
	private static ArrayList<CalculoModel> list = null;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DashboardModel dashboardModel = null;
	private Auxilio aux = null;

	public App(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
	}

	// VERIFICAÇÃO DO TIPO DO SISTEMA DE AMORTIZAÇÃO SELECIONADO
	public ArrayList<CalculoModel> sys() throws ServletException, IOException {
		if (this.request.getParameter("calcular") != null) {
			if (this.request.getParameter("sys_amort").trim().equals("Gaus")) {
				list = new CalGausController(this.request, this.response).listaCalGausModel();

			} else if (this.request.getParameter("sys_amort").trim().equals("PRICE")) {
				list = new CalPriceController(this.request, this.response).listaCalPriceModel();

			} else if (this.request.getParameter("sys_amort").trim().equals("SAC")) {
				list = new CalSacController(this.request, this.response).listaCalSacModel();

			} else if (this.request.getParameter("sys_amort").trim().equals("SAC.JS")) {
				list = new CalSacjsController(this.request, this.response).listaCalSacjsModel();

			} else if (this.request.getParameter("sys_amort").trim().equals("SACRE")) {
				list = new CalSacreController(this.request, this.response).listaCalSacreModel();

			} else if (this.request.getParameter("sys_amort").trim().equals("MAJS/ Hamburgues")) {
				list = new CalMajsController(this.request, this.response).listaCalMajsInversaoModel();
			}
		}
		return list;
	}

	// TRATAMENTO DAS TAXAS MENSAIS E ANUAIS
	public DashboardModel sys_tax() {
		if (this.request.getParameter("calcular") != null) {
			if (this.request.getParameter("mensal_anual").equals("Anual")) {
				dashboardModel.setTaxaMensal(this.getTaxaEquivalenteMensal());
				dashboardModel.setTaxaAnual(dashboardModel.getTaxa());
				return dashboardModel;
			}
		}

		dashboardModel.setTaxaMensal(this.dashboardModel.getTaxa());
		dashboardModel.setTaxaAnual(this.getTaxaEquivalenteAnual());
		return dashboardModel;
	}

	// TAXA EQUIVALENTE MENSAL
	public double getTaxaEquivalenteMensal() {
		aux = new Auxilio();
		aux.setDoubleAux(this.dashboardModel.getTaxa() / this.dashboardModel.getPrazo());
		return aux.getDoubleAux();
	}

	// TAXA EQUIVALENTE ANUAL
	public double getTaxaEquivalenteAnual() {
		aux = new Auxilio(2);
		aux.adicionaIntAnyArray(0, 1);
		aux.adicionaIntAnyArray(1, 100);
		aux.setDoubleAux((Math.pow(aux.getIntAnyArray()[0] + (this.dashboardModel.getTaxa() / aux.getIntAnyArray()[1]),
				dashboardModel.getPrazo()) - aux.getIntAnyArray()[0]) * aux.getIntAnyArray()[1]);
		return aux.getDoubleAux();
	}

	// PEGAR VALORES INFORMADOS NA PAGINA
	public DashboardModel getDashboardModel() {
		dashboardModel = new DashboardModel();
		if (this.request.getParameter("calcular") != null) {
			dashboardModel.setValorEmprestFinancia(Double.parseDouble(
					SistemaController.isNullOrEmpty(this.request.getParameter("emprest_financia").trim())));

			dashboardModel.setTaxa(
					Double.parseDouble(SistemaController.isNullOrEmpty(this.request.getParameter("taxa").trim())));

			dashboardModel.setTaxaMensal(Double.parseDouble(SistemaController
					.clean(SistemaController.isNullOrEmpty(this.request.getParameter("taxa_mensal").trim()))));

			dashboardModel.setTaxaAnual(Double.parseDouble(SistemaController
					.clean(SistemaController.isNullOrEmpty(this.request.getParameter("taxa_anual").trim()))));

			dashboardModel.setPrazo(
					Integer.parseInt(SistemaController.isNullOrEmpty(this.request.getParameter("prazo").trim())));

			dashboardModel.setDataPrimeiraParcela(Date.valueOf(this.request.getParameter("ultima_parcela").trim()));
		}
		return dashboardModel;
	}
}