package com.simor.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalSacController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private double[] balaoArray = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaSac = null;

	public CalSacController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = new App(request, response).getDashboardModel();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		calculoModel.setJuroInicial(this.dashboardModel.getTaxa());
	}

	public CalSacController() {
		super();
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaSacCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalSacModel() {
		listaSac = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setJuroInicial(this.getTaxaSacCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
			calculoModel.setAmortizacao(calculoModel.getValorEmprestFinac() / 11);
			calculoModel.setPrestacao(0);

			double somaJuro = 0;
			if (this.dashboardModel.getCarencia() > 0) {
				if (this.dashboardModel.getTipoCarencia().equals("Sem juros")) {
					for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
						calculo = new CalculoModel();
						calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());

						if (i < this.dashboardModel.getCarencia()) {
							somaJuro += calculoModel.getJuro();
						}
						if ((i + 1) > this.dashboardModel.getCarencia()) {
							calculoModel.setAmortizacao((this.dashboardModel.getValorEmprestFinancia() + somaJuro)
									/ (this.dashboardModel.getPrazo() - this.dashboardModel.getCarencia()));
							calculoModel.setValorEmprestFinac(
									calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
							calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());

						} else {
							calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
							calculoModel.setValorEmprestFinac(
									calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
							calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
						}

						calculo.setJuro(calculoModel.getJuro());
						calculo.setAmortizacao(calculoModel.getAmortizacao());
						calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
						// ********************
						calculo.setBalao(this.listaBalao()[i]);
						// ********************
						calculo.setDataVencimento(SistemaController.listaDataVencimento(
								SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
								this.dashboardModel.getPrazo()).get(i).getDataVencimento());

						aux = new Auxilio();
						aux.setDoubleAux(0.00001);
						aux.setIntAux(0);
						calculo.setAuxilio(aux);
						listaSac.add(calculo);
					}
				} else if (this.dashboardModel.getTipoCarencia().equals("Com juros")) {
					for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
						calculo = new CalculoModel();

						if (i < this.dashboardModel.getCarencia()) {
							somaJuro += calculoModel.getJuro();
						}
						if ((i + 1) > this.dashboardModel.getCarencia()) {
							calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
							calculoModel.setAmortizacao((this.dashboardModel.getValorEmprestFinancia())
									/ (this.dashboardModel.getPrazo() - this.dashboardModel.getCarencia()));
							calculoModel.setValorEmprestFinac(
									calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
							calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());

						} else {
							calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
							calculoModel.setValorEmprestFinac(
									calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
							calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
						}

						if (i < this.dashboardModel.getCarencia()) {
							calculoModel.setAmortizacao(0);
							calculoModel.setJuro(
									calculoModel.getJuroInicial() * this.dashboardModel.getValorEmprestFinancia());
							calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
							calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
						}

						calculo.setJuro(calculoModel.getJuro());
						calculo.setAmortizacao(calculoModel.getAmortizacao());
						calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
						// ********************
						calculo.setBalao(this.listaBalao()[i]);
						// ********************
						calculo.setDataVencimento(SistemaController.listaDataVencimento(
								SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
								this.dashboardModel.getPrazo()).get(i).getDataVencimento());

						aux = new Auxilio();
						aux.setDoubleAux(0.00001);
						aux.setIntAux(0);
						calculo.setAuxilio(aux);
						listaSac.add(calculo);
					}
				}

			} else {

				calculoModel.setAmortizacao(calculoModel.getValorEmprestFinac() / this.dashboardModel.getPrazo());
				for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
					calculo = new CalculoModel();
					calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
					calculoModel
							.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
					calculo.setPrestacao(calculoModel.getAmortizacao() + calculoModel.getJuro());
					// ********************
					calculo.setBalao(this.listaBalao()[i]);
					// ********************
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
					listaSac.add(calculo);
				}
			}
		}
		return listaSac;
	}

	// CALCULAR BALAO
	public double[] listaBalao() {
		balaoArray = new double[this.dashboardModel.getPrazo()];
		for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
			if (this.dashboardModel.getPeriodicidadeBalao() != 0) {
				if (((i + 1) % this.dashboardModel.getPeriodicidadeBalao() == 0) && (i != 0)) {
					balaoArray[i] = this.dashboardModel.getValorBalao();
				} else {
					balaoArray[i] = 0;
				}
			} else {
				balaoArray[i] = 0;
			}
		}
		return balaoArray;
	}
}
