package com.simor.controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Appo extends CalFinanceiraController{
	protected ArrayList<Double> list=null;
	public Appo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super(request,response);
	}
	
	public ArrayList<Double>sys_cal(){
		this.list=new ArrayList<Double>();
		this.list.add(this.valorPagar());
		this.list.add(this.totalPago());
		return this.list;
	}
}
