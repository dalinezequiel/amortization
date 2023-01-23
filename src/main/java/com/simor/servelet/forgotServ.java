package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.controller.ContaController;
import com.simor.dao.ContaDAO;
import com.simor.model.*;

/**
 * Servlet implementation class forgotServ
 */
public class forgotServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContaModel contaModel=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forgotServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		contaModel=new ContaModel();
		contaModel.setIdConta(ContaController.getUserId());
		contaModel.setPerfil(request.getParameter("perfil"));
		contaModel.setEmail(request.getParameter("email"));
		contaModel.setUsuario(request.getParameter("usuario"));
		contaModel.setSenha(request.getParameter("senha"));
		contaModel.setComfirmaSenha(request.getParameter("comfirma_senha"));
		int ano = 2020;
		request.setAttribute("ano", ano);
		//request.getContextPath();
		ContaDAO.recuperarConta(contaModel);
		//request.getRequestDispatcher("page/forgot.jsp").include(request, response);
		response.sendRedirect("page/forgot.jsp");
	}
}
