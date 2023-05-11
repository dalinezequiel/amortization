package com.simor.controller;

import java.io.IOException;

import com.simor.model.CalFinanceiraModel;
import com.simor.model.DashboardModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalFinanceiraController {
	private CalFinanceiraModel fin = null;
	private DashboardModel dashboardModel = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public CalFinanceiraController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		fin = new CalFinanceiraModel();
		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
		
		System.out.println("Prest: "+this.dashboardModel.getValorPrestacao());
		System.out.println("Taxa: "+this.dashboardModel.getTaxa());
		System.out.println("Prazo: "+this.dashboardModel.getPrazo());
		System.out.println("Adicional: "+this.dashboardModel.getValorAdicional());
		System.out.println();
		System.out.println("Emprestimo: "+this.emprestimo());
		System.out.println("Valor a Pagar: "+this.valorPagar());
		System.out.println("Total Pago: "+this.totalPago());
	}

	public double valorPagar() {
		this.fin.setValorPagar(this.dashboardModel.getValorPrestacao() * this.dashboardModel.getPrazo());
		return this.fin.getValorPagar();
	}
	
	public double totalPago() {
		this.fin.setValorPago(this.valorPagar()+this.dashboardModel.getValorAdicional());
		return this.fin.getValorPago();
	}
	
	public double emprestimo(){
		double v1=(Math.pow(this.dashboardModel.getValorPrestacao()*(1+this.dashboardModel.getTaxa()/100), this.dashboardModel.getPrazo())-1);
		double v2=Math.pow(1+this.dashboardModel.getTaxa()/100, this.dashboardModel.getPrazo())*(this.dashboardModel.getTaxa()/100);
		return v1/v2;
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
			dashboardModel.setValorPrestacao(Double.parseDouble(SistemaController
					.clean(SistemaController.isNullOrEmpty(this.request.getParameter("parcela").trim()))));

			dashboardModel.setTaxa(Double.parseDouble(SistemaController
					.cleanTax(SistemaController.isNullOrEmpty(this.request.getParameter("taxa").trim()))));

			dashboardModel.setPrazo(
					Integer.parseInt(SistemaController.isNullOrEmpty(this.request.getParameter("prazo").trim())));
			
			dashboardModel.setValorAdicional(Double.parseDouble(SistemaController
					.cleanTax(SistemaController.isNullOrEmpty(this.request.getParameter("adicional").trim()))));
		}
		return dashboardModel;
	}
}
