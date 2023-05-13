package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.simor.dao.AccountDAO;
import com.simor.model.ContaModel;
import com.simor.model.TransitModel;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContaModel contaModel = null;
	private TransitModel transit = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		contaModel.setEmail(request.getParameter("usr_email").trim());
		contaModel.setUsuario(request.getParameter("usr_email").trim());
		contaModel.setSenha(request.getParameter("pass").trim());
		if (AccountDAO.login(contaModel)) {
			transit = new TransitModel();
			if ((!contaModel.getUsuario().isEmpty() && contaModel.getUsuario() != null)
					&& (!contaModel.getEmail().isEmpty() && contaModel.getEmail() != null)) {
				transit.setTransit(contaModel.getEmail());
				AccountDAO.updateTransit(transit);

			} else if (!contaModel.getUsuario().isEmpty() && contaModel.getUsuario() != null) {
				transit.setTransit(contaModel.getUsuario());
				AccountDAO.updateTransit(transit);

			} else if (!contaModel.getEmail().isEmpty() && contaModel.getEmail() != null) {
				transit.setTransit(contaModel.getEmail());
				AccountDAO.updateTransit(transit);
			} else {
				transit.setTransit("user.default");
				AccountDAO.updateTransit(transit);
			}
			
			response.sendRedirect("page/home.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}
