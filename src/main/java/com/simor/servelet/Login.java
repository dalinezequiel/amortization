package com.simor.servelet;

import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.dao.ConnectionDAO;
import com.simor.dao.ContaDAO;
import com.simor.model.ContaModel;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContaModel contaModel = null;

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
		
		//ConnectionDAO.readProperties();
		//System.out.println("Servelet: "+ContaDAO.login(contaModel));
//		if (ContaDAO.login(contaModel)) {
//			response.sendRedirect("page/home.jsp");
//		} else {
//			response.sendRedirect("index.jsp");
//		}
		response.sendRedirect("page/home.jsp");
	}

}
