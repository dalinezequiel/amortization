package com.simor.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.simor.model.ComparadorModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Appe {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	private CalGausCompare gaus=null;
	private CalPriceCompare price=null;
	private CalSalCompare sal=null;
	private CalSacreCompare sacre=null;
	private CalSacjsCompare sacjs=null;
	private CalSacCompare sac=null;
	private CalMajsCompare majs=null;
	private CalNPCompare np=null;
	
	private ArrayList<ComparadorModel> list=null;

	public Appe(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		new CalNPCompare(this.request,this.response);
	}
	
	public ArrayList<ComparadorModel> sys_appe() throws ServletException, IOException{
		this.list=new ArrayList<ComparadorModel>();

		/*PRICE*/
		this.price=new CalPriceCompare(this.request,this.response);
		this.list.add(this.price.sys_price());
		
		/*SAC*/
		this.sac=new CalSacCompare(this.request,this.response);
		this.list.add(this.sac.sys_sac());
		
		/*MAJS*/
		this.majs=new CalMajsCompare(this.request,this.response);
		this.list.add(this.majs.sys_majs());
		
		/*SAL*/
		this.sal=new CalSalCompare(this.request,this.response);
		this.list.add(this.sal.sys_sal());
		
		/*GAUS*/
		this.gaus=new CalGausCompare(this.request,this.response);
		this.list.add(this.gaus.sys_gaus());
		
		/*SAC-JS*/
		this.sacjs=new CalSacjsCompare(this.request,this.response);
		this.list.add(this.sacjs.sys_sacjs());
		
		/*SACRE*/
		this.sacre=new CalSacreCompare(this.request,this.response);
		this.list.add(this.sacre.sys_sacre());
		
		/*NÃO PERIODICAS*/
		this.np=new CalNPCompare(this.request,this.response);
		this.list.add(this.np.sys_np());
		
		return this.list;
	}
}
