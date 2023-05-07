package com.simor.controller;

import java.io.IOException;

import java.util.ArrayList;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalMajsController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaMajs, listaInversao = null;

	public CalMajsController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = new App(request, response).getDashboardModel();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		calculoModel.setJuroInicial(this.dashboardModel.getTaxa());
	}

	public CalMajsController() {
		super();
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaMajsCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalMajsModel() {
		listaMajs = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setJuroInicial(this.getTaxaMajsCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
			calculoModel.setAmortizacao(calculoModel.getValorEmprestFinac() / this.dashboardModel.getPrazo());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
				calculo.setJuro(calculoModel.getJuro());
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				//calculo.setDataVencimento(calculoModel.getDataVencimento());
				calculo.setDataVencimento(SistemaController.listaDataVencimento(
						SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
						this.dashboardModel.getPrazo()).get(i).getDataVencimento());
				listaMajs.add(calculo);
			}
		}
		return listaMajs;
	}
	
	public ArrayList<CalculoModel> listaCalMajsInversaoModel() {
		listaInversao = new ArrayList<CalculoModel>();
		listaMajs = this.listaCalMajsModel();
		if (listaMajs != null) {
			for(int i=0; i<listaMajs.size(); i++) {
				calculo = new CalculoModel();
				calculo.setPrestacao(listaMajs.get(dashboardModel.getPrazo() - (i+1)).getPrestacao());
				calculo.setJuro(listaMajs.get(dashboardModel.getPrazo() - (i+1)).getJuro());
				calculo.setAmortizacao(listaMajs.get(i).getAmortizacao());
				calculo.setValorEmprestFinac(listaMajs.get(i).getValorEmprestFinac());
				calculo.setDataVencimento(listaMajs.get(i).getDataVencimento());
				
				aux = new Auxilio();
				aux.setDoubleAux(0.00001);
				aux.setIntAux(0);
				calculo.setAuxilio(aux);
				listaInversao.add(calculo);
			}
		}
		return listaInversao;
	}
}
