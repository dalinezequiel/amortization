package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.model.CacheModel;
import com.simor.model.HomeModel;
import com.simor.controller.*;
import com.simor.dao.CacheDAO;
import com.simor.dao.ConnectionDAO;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HomeModel homeModelGlobal = null;
	private HomeCalculoController homeCalculoGlobal = null;
	private CacheModel cacheModelGlobal = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		homeModelGlobal = new HomeModel();
		cacheModelGlobal = new CacheModel();
		// hc = new HomeCalculoController();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		homeCalculoGlobal = new HomeCalculoController(this.requestHomeModel(request, response));

		this.cacheModelGlobal.setPrestacao(this.homeCalculoGlobal.getCalculoDePrestacao());
		this.cacheModelGlobal.setJuro(this.homeCalculoGlobal.getCalculoDeJuro());
		this.cacheModelGlobal.setAmortizacao(this.homeCalculoGlobal.getCalculoDeAmortizacao());
		this.cacheModelGlobal.setSaldoDevedor(this.homeCalculoGlobal.getCalculoDeSaldoDevedor());

		/*System.out.println(homeCalculoGlobal.formateMoeda(this.cacheModelGlobal.getPrestacao()));
		System.out.println(homeCalculoGlobal.formateMoeda(this.cacheModelGlobal.getJuro()));
		System.out.println(homeCalculoGlobal.formateMoeda(this.cacheModelGlobal.getAmortizacao()));
		System.out.println(homeCalculoGlobal.formateMoeda(this.cacheModelGlobal.getSaldoDevedor()));*/

		 CacheDAO.insertIntoCache(this.cacheModelGlobal);
		 System.out.println(ConnectionDAO.getConnection());
		
		//if (CacheDAO.insertIntoCache(cacheModelGlobal)) {
			response.sendRedirect("page/home.jsp");
		//}
	}

	public HomeModel requestHomeModel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.homeModelGlobal
				.setValorEmprestFinancia(this.isNullOrEmpty(request.getParameter("emprest_financia").trim()));
		this.homeModelGlobal.setTaxa(Double.parseDouble(this.isNullOrEmpty(request.getParameter("taxa").trim())));
		this.homeModelGlobal
				.setTaxaMensal(Double.parseDouble(this.isNullOrEmpty(request.getParameter("taxa_mensal").trim())));
		this.homeModelGlobal
				.setTaxaAnual(Double.parseDouble(this.isNullOrEmpty(request.getParameter("taxa_anual").trim())));
		this.homeModelGlobal.setPrazo(Integer.parseInt(this.isNullOrEmpty(request.getParameter("prazo").trim())));

		return this.homeModelGlobal;
	}

	public String isNullOrEmpty(String value) {
		if (value.isEmpty() || value == null) {
			return "0";
		}
		return value;
	}

	public boolean canPrint() {
		if (this.homeModelGlobal != null) {
			return true;
		}
		return false;
	}

	public HomeModel getHomeModel() {
		return this.homeModelGlobal;
	}
}
