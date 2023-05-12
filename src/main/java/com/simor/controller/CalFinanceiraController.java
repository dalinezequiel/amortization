package com.simor.controller;

import java.io.IOException;

import com.simor.model.CalFinanceiraModel;
import com.simor.model.DashboardModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalFinanceiraController {
	protected CalFinanceiraModel calulate = null;
	protected DashboardModel dashboardModel = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public CalFinanceiraController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calulate = new CalFinanceiraModel();
		this.request = request;
		this.response = response;
		this.dashboardModel = this.getDashboardModel();
	}

	// VALOR A PAGAR
	protected double valorPagar() {
		this.calulate.setValorPagar(this.dashboardModel.getValorPrestacao() * this.dashboardModel.getPrazo());
		return this.calulate.getValorPagar();
	}

	// TOTAL PAGO
	protected double totalPago() {
		this.calulate.setValorPago(this.valorPagar() + this.dashboardModel.getValorAdicional());
		return this.calulate.getValorPago();
	}

	// VALOR DO EMPRESTIMO
	protected double valorEmprestimo() {
		this.calulate.setValorEmprestimo(this.dashboardModel.getValorPrestacao()
				* ((1 - Math.pow(1 + (this.dashboardModel.getTaxa() / 100), -this.dashboardModel.getPrazo()))
						/ (this.dashboardModel.getTaxa() / 100)));
		return this.calulate.getValorEmprestimo();
	}

	// EVENTO CLICK
	protected boolean calcular_click() throws ServletException, IOException {
		if (this.request.getParameter("calcular") != null) {
			return true;
		}
		return false;
	}

	// PEGAR VALORES INFORMADOS NA PAGINA
	protected DashboardModel getDashboardModel() throws ServletException, IOException {
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
