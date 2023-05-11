package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.controller.ContaController;
import com.simor.dao.AccountDAO;
import com.simor.model.ContaModel;

/**
 * Servlet implementation class Forgot
 */
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContaModel contaModel = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Forgot() {
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

		if (AccountDAO.recovery(contaModel)) {
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("page/forgot.jsp");
		}
	}

}
