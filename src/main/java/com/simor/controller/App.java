package com.simor.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import com.simor.model.*;

public class App extends DashboardController {
	protected AppModel appModel = null;

	public App(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super(request, response);
		this.appModel = new AppModel();
	}

	// FAZER O SET DOS OBJECTOS NECESSARIOS PARA FUNCIONAMENTO DA PÁGINA DASHBOARD
	public AppModel sys_all() throws ServletException, IOException {
		this.appModel.setList(this.sys());
		this.appModel.setTax(this.sys_tax());
		return this.appModel;
	}
}
