package com.simor.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSacreController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaSacre = null;

	public CalSacreController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = new App(request, response).getDashboardModel();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		calculoModel.setJuroInicial(this.dashboardModel.getTaxa());
	}

	public CalSacreController() {
		super();
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		
		//PRIMEIROMCALCULO
		aux.adicionaDoubleAnyArray(0, this.dashboardModel.getValorEmprestFinancia() / this.dashboardModel.getPrazo());
		
		//PRIMEIROMCALCULO
		aux.adicionaDoubleAnyArray(1, aux.getDoubleAnyArray()[0] + (this.getTaxaSacreCalculado() * this.dashboardModel.getValorEmprestFinancia()));
		calculoModel.setPrestacao(aux.getDoubleAnyArray()[1]);
		return calculoModel.getPrestacao();
	}
	
	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaSacreCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalSacreModel() {
		listaSacre = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setJuroInicial(this.getTaxaSacreCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
			//calculoModel.setAmortizacao(calculoModel.getValorEmprestFinac() / this.dashboardModel.getPrazo());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setAmortizacao(this.getCalculoDePrestacao() - calculoModel.getJuro());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				
				calculo.setPrestacao(this.getCalculoDePrestacao());
				calculo.setJuro(calculoModel.getJuro());
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				//calculo.setDataVencimento(calculoModel.getDataVencimento());
				calculo.setDataVencimento(SistemaController.listaDataVencimento(
						SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
						this.dashboardModel.getPrazo()).get(i).getDataVencimento());
				
				aux = new Auxilio();
				aux.setDoubleAux(0.00001);
				aux.setIntAux(0);
				calculo.setAuxilio(aux);
				listaSacre.add(calculo);
			}
		}
		return listaSacre;
	}
}
