package com.simor.servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import com.simor.controller.SistemaController;
import com.simor.dao.HolidayDAO;
import com.simor.model.*;

/**
 * Servlet implementation class FeriadoServ
 */
public class FeriadoServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FeriadoModel feria = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FeriadoServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (this.feriadoParameter(request, response) != null) {
			if (HolidayDAO.insert(this.feriadoParameter(request, response))) {
				response.sendRedirect("page/holiday.jsp");
			}
		} else {
			response.sendRedirect("page/holiday.jsp");
		}
	}

	protected FeriadoModel feriadoParameter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		feria = new FeriadoModel();
		if (request.getParameter("descricao").trim() != null && !(request.getParameter("descricao").trim().isEmpty())) {
			feria.setCodigo(SistemaController.getId());
			feria.setDataFeriado(Date.valueOf(LocalDate.parse(request.getParameter("data_feriado").trim())));
			feria.setDescricao(request.getParameter("descricao").trim());
			feria.setAno(Integer.parseInt(request.getParameter("ano").trim()));
			return feria;
		}
		return null;
	}
}
