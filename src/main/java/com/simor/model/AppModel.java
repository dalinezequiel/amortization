package com.simor.model;

import java.util.*;

public class AppModel {
	private ArrayList<CalculoModel> listCalculo;
	private ArrayList<DashboardModel> listDashboardModel;

	public AppModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<CalculoModel> getListCalculo() {
		return listCalculo;
	}

	public void setListCalculo(ArrayList<CalculoModel> listCalculo) {
		this.listCalculo = listCalculo;
	}

	public ArrayList<DashboardModel> getListDashboardModel() {
		return listDashboardModel;
	}

	public void setListDashboardModel(ArrayList<DashboardModel> listDashboardModel) {
		this.listDashboardModel = listDashboardModel;
	}
}
