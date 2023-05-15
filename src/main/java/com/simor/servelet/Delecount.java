package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.dao.AccountDAO;

/**
 * Servlet implementation class Delecount
 */
public class Delecount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Delecount() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("delete_account").trim() != null
				&& !request.getParameter("delete_account").trim().isBlank()) {
			if (AccountDAO.deleteAccountId(Integer.parseInt(request.getParameter("delete_account").trim()))) {
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("page/configuration.jsp");
			}
		} else {
			response.sendRedirect("page/configuration.jsp");
		}
	}
}
