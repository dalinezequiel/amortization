package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.simor.model.*;
import com.simor.controller.*;
import com.simor.dao.ContaDAO;

/**
 * Servlet implementation class ContaServ
 */
public class ContaServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ContaModel contaModel=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContaServ() {
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
		
		ContaDAO.insertConta(contaModel);
		response.sendRedirect("page/credencial.jsp");
		
		System.out.println(contaModel.getPerfil()+"\n"+contaModel.getUsuario());
	}
}
