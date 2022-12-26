package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.model.HomeModel;
import com.simor.controller.*;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HomeModel hm = null;
	private HomeCalculoController hc = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		//hc = new HomeCalculoController();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		hc = new HomeCalculoController(this.requestHomeModel(request, response));
		System.out.println(hc.getCalculoDePrestacao());
		
		//System.out.println(hm.getValorEmprestFinancia());
		//System.out.println(hc.calcularPrestacao(this.requestHomeModel(request, response)));
		//System.out.println(this.isNullOrEmpty(this.requestHomeModel(request, response).getValorEmprestFinancia(),true));
	}

	public HomeModel requestHomeModel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		hm = new HomeModel();
		hm.setValorEmprestFinancia(request.getParameter("emprest_financia").trim());
		hm.setTaxa(Double.parseDouble(request.getParameter("taxa").trim()));
		hm.setTaxaMensal(Double.parseDouble(request.getParameter("taxa_mensal").trim()));
		hm.setTaxaAnual(Double.parseDouble(request.getParameter("taxa_anual").trim()));
		hm.setPrazo(Integer.parseInt(request.getParameter("prazo").trim()));
		return hm;
	}

	public String isNullOrEmpty(String valor, boolean isNumber) {
		if (valor.isEmpty() || valor == null) {
			if (isNumber) {
				return "0";
			} else {
				return "";
			}
		}
		return valor;
	}
}
