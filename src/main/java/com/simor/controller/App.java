package com.simor.controller;

import com.simor.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class App {
	private static ArrayList<CalculoModel> list = null;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public App(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
	}

	public ArrayList<CalculoModel> sys() throws ServletException, IOException {
		if (this.request.getParameter("calcular") != null) {
			if (this.request.getParameter("sys_amort").trim().equals("Gaus")) {
				list = new CalGausController(this.request, this.response).listaCalGausModel();
				
			} else if (this.request.getParameter("sys_amort").trim().equals("PRICE")) {
				list = new CalPriceController(this.request, this.response).listaCalPriceModel();
				
			} else if (this.request.getParameter("sys_amort").trim().equals("SAC")) {
				list = new CalSacController(this.request, this.response).listaCalSacModel();
				
			} else if (this.request.getParameter("sys_amort").trim().equals("SAC.JS")) {
				list = new CalSacjsController(this.request, this.response).listaCalSacjsModel();
				
			} else if (this.request.getParameter("sys_amort").trim().equals("SACRE")) {
				list = new CalSacreController(this.request, this.response).listaCalSacreModel();
				
			} else if (this.request.getParameter("sys_amort").trim().equals("MAJS/ Hamburgues")) {
				list = new CalMajsController(this.request, this.response).listaCalMajsInversaoModel();
			}
		}
		return list;
	}
}
