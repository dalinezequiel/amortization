package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.simor.dao.AccountDAO;
import com.simor.model.ContaModel;
import com.simor.model.ProfileModel;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContaModel contaModel = null;
	private ProfileModel profile = null;

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
		contaModel.setEmail(request.getParameter("usr_email").trim().toLowerCase().replace(" ", ""));
		contaModel.setUsuario(request.getParameter("usr_email").trim().toLowerCase().replace(" ", ""));
		contaModel.setSenha(request.getParameter("pass").trim());
		if (AccountDAO.login(contaModel)) {
			profile = new ProfileModel();
			if ((!contaModel.getUsuario().isEmpty() && contaModel.getUsuario() != null)
					&& (!contaModel.getEmail().isEmpty() && contaModel.getEmail() != null)) {
				profile.setProfile(contaModel.getEmail());
				AccountDAO.updateProfile(profile);

			} else if (!contaModel.getUsuario().isEmpty() && contaModel.getUsuario() != null) {
				profile.setProfile(contaModel.getUsuario());
				AccountDAO.updateProfile(profile);

			} else if (!contaModel.getEmail().isEmpty() && contaModel.getEmail() != null) {
				profile.setProfile(contaModel.getEmail());
				AccountDAO.updateProfile(profile);
			} else {
				profile.setProfile("user.blank");
				AccountDAO.updateProfile(profile);
			}
			
			response.sendRedirect("page/panel.jsp");
		} else {
			response.sendRedirect("index.jsp");
		}
	}
}
