package com.simor.controller;

import java.io.IOException;

import com.simor.model.*;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalNPController /*extends SerieNPController*/ {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private static ArrayList<CalculoModel> listaNP, list = null;
	private Auxilio aux = null;
	private int percentagem;
	private SerieNPController serieNP=null;

	public CalNPController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//super(0.01);
		dashboardModel = new DashboardModel();
		aux = new Auxilio();
		calculoModel = new CalculoModel();
		this.dashboardModel = new App(request, response).getDashboardModel();
		percentagem = 100;
		serieNP=new SerieNPController(this.getTaxaNPCalculado());
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao(double valor) {
		return this.serieNP.coeficiente() * valor;
	}

	// CALCULAR JUROS
	public double getCalculoJurosNP(int mes, double valor) {
		return (this.serieNP.taxaMensal(mes) / percentagem) * valor;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalNPModel() {
		listaNP = new ArrayList<CalculoModel>();
		System.out.println("Prest: " + dashboardModel.getValorEmprestFinancia());
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao(calculoModel.getValorEmprestFinac()));
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(this.getCalculoJurosNP((i + 1), calculoModel.getValorEmprestFinac()));
				calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getPrestacao());
				calculo.setJuro(calculoModel.getJuro());
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				calculo.setDataVencimento(SistemaController.listaDataVencimento(
						SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
						this.dashboardModel.getPrazo()).get(i).getDataVencimento());

				aux = new Auxilio();
				aux.setDoubleAux(0.00001);
				aux.setIntAux(0);
				calculo.setAuxilio(aux);
				listaNP.add(calculo);
			}
		}
		return listaNP;
	}

	public void listNP() {
		list = this.listaCalNPModel();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getPrestacao());
		}
	}

	// CALCULAR PRECENTUAL
	public double getTaxaNPCalculado() {
		this.dashboardModel.setTaxa(1);
		return this.dashboardModel.getTaxa() / 100;
	}
}
