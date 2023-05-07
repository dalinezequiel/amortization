package com.simor.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.simor.model.Auxilio;
import com.simor.model.CalculoModel;
import com.simor.model.DashboardModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSalController  {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaSal = null;
	private SalController salcon = null;

	public CalSalController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = new App(request, response).getDashboardModel();
		salcon = new SalController((float) this.getTaxaSalCalculado(), this.dashboardModel.getPrazo());
	}

	public CalSalController() {
		dashboardModel = new DashboardModel();
		this.dashboardModel.setPrazo(2);
		this.dashboardModel.setTaxa(1);

		calculoModel = new CalculoModel();
		aux = new Auxilio();
		salcon = new SalController((float) this.getTaxaSalCalculado(), this.dashboardModel.getPrazo());
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao(double valor) {
		return this.salcon.factor() * valor;
	}

	// CALCULAR JUROS
	public double getCalculoJurosSal(int mes, double valor) {
		return this.getCalculoDePrestacao(valor) - this.getCalculoAmortizacao(mes, valor);
	}

	// CALCULAR AMORTIZACAO
	public double getCalculoAmortizacao(int mes, double valor) {
		return this.getCalculoDePrestacao(valor) / (1 + mes * this.getTaxaSalCalculado());
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalSalModel() {
		listaSal = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao(calculoModel.getValorEmprestFinac()));
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(this.getCalculoJurosSal((i + 1), this.dashboardModel.getValorEmprestFinancia()));
				calculoModel
						.setAmortizacao(getCalculoAmortizacao((i + 1), this.dashboardModel.getValorEmprestFinancia()));
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
				listaSal.add(calculo);
			}
		}
		return listaSal;
	}

	// CALCULAR PRECENTUAL
	public double getTaxaSalCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	public ArrayList<Double> initial() {
		return this.salcon.initial();
	}

	public double sum() {
		return this.salcon.sum();
	}

	public double division() {
		return this.salcon.division();
	}

	public double factor() {
		return this.salcon.factor();
	}

//	public static void main(String[] args) {
//		CalSalController sal = new CalSalController();
//		System.out.println("test for list: " + sal.initial());
//		System.out.println("test for list sum: " + sal.sum());
//		System.out.println("test sum division: " + sal.division());
//		System.out.println("test sum factor: " + sal.factor());
//		System.out.println();
//		System.out.println();
//		System.out.println("prestacao: " + sal.getCalculoDePrestacao(50000));
//		System.out.println("amortizacao: " + sal.getCalculoAmortizacao(2, 50000));
//		System.out.println("juros: " + sal.getCalculoJurosSal(2, 50000));
//	}
}
