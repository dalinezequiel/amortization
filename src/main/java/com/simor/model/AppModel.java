package com.simor.model;

import java.util.*;

public class AppModel {
	private ArrayList<CalculoModel> list;
	private DashboardModel tax;
	private DashboardModel latePayment;

	public AppModel() {
		super();
	}

	public ArrayList<CalculoModel> getList() {
		return list;
	}

	public void setList(ArrayList<CalculoModel> list) {
		this.list = list;
	}

	public DashboardModel getTax() {
		return tax;
	}

	public void setTax(DashboardModel tax) {
		this.tax = tax;
	}

	public DashboardModel getLatePayment() {
		return latePayment;
	}

	public void setLatePayment(DashboardModel latePayment) {
		this.latePayment = latePayment;
	}
}
