package com.simor.servelet;

//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.controller.ContaController;
import com.simor.dao.ContaDAO;
import com.simor.model.ContaModel;

/**
 * Servlet implementation class Account
 */
//@WebServlet(urlPatterns= {})
//@WebServlet("/principal")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContaModel contaModel = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
//		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		contaModel = new ContaModel();
		contaModel.setIdConta(ContaController.getUserId());
		contaModel.setEmail(request.getParameter("email"));
		contaModel.setUsuario(request.getParameter("usuario"));
		contaModel.setSenha(request.getParameter("senha"));
		contaModel.setComfirmaSenha(request.getParameter("comfirma_senha"));

		if (ContaDAO.insertConta(contaModel)) {
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("page/credencial.jsp");
		}
		// doGet(request, response);

//		RequestDispatcher red=request.getRequestDispatcher("page/credencial.jsp");
//		red.forward(request, response);
	}

}
