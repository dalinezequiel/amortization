package com.simor.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

import com.simor.model.*;

public class CalPriceController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaPrice = null;

	public CalPriceController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = new App(request, response).getDashboardModel();
		calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
		calculoModel.setJuroInicial(this.dashboardModel.getTaxa());
	}

	public DashboardModel dashboardModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		aux.setIntAux(1);

		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux()
				+ getPercentualPriceCalculado(dashboardModel.getValorEmprestFinancia(), dashboardModel.getTaxa()));

		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], dashboardModel.getPrazo()));

		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[1]
				* getPercentualPriceCalculado(dashboardModel.getValorEmprestFinancia(), dashboardModel.getTaxa())
				/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));

		// QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, dashboardModel.getValorEmprestFinancia() * aux.getDoubleAnyArray()[2]);

		calculoModel.setPrestacao(aux.getDoubleAnyArray()[3]);
		return calculoModel.getPrestacao();
	}

	// CALCULAR PRECENTUAL
	private double getPercentualPriceCalculado(double valorEmprestFinanc, double juroInicial) {
		return juroInicial / 100;
	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaPriceCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
//	public ArrayList<CalculoModel> listaCalPriceModel() {
//		listaPrice = new ArrayList<CalculoModel>();
//		if (this.dashboardModel != null) {
//			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
//			calculoModel.setPrestacao(this.getCalculoDePrestacao());
//			calculoModel.setJuroInicial(this.getTaxaPriceCalculado());
//			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
//
//			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
//				calculo = new CalculoModel();
//				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
//				calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
//				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
//				calculo.setPrestacao(calculoModel.getPrestacao());
//				calculo.setJuro(calculoModel.getJuro());
//				calculo.setAmortizacao(calculoModel.getAmortizacao());
//				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
//				calculo.setDataVencimento(SistemaController.listaDataVencimento(
//						SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
//						this.dashboardModel.getPrazo()).get(i).getDataVencimento());
//				aux = new Auxilio();
//				aux.setDoubleAux(0.00001);
//				aux.setIntAux(0);
//				calculo.setAuxilio(aux);
//				listaPrice.add(calculo);
//			}
//		}
//		return listaPrice;
//	}
	int car=2;
	public ArrayList<CalculoModel> listaCalPriceModel() {
		listaPrice = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao());
			calculoModel.setJuroInicial(this.getTaxaPriceCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
			
			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				if((i+1) > car) {
					calculo = new CalculoModel();
					calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
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
					listaPrice.add(calculo);
					
				}else {
					calculo = new CalculoModel();
//					calculoModel.setPrestacao(0);
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
					listaPrice.add(calculo);

				}
			}
		}
		return listaPrice;
	}
	
//	int car=4;
//	public ArrayList<CalculoModel> listaCalGausModel() {
//		listaGaus = new ArrayList<CalculoModel>();
//		if (this.dashboardModel != null) {
//			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
//			calculoModel.setPrestacao(this.getCalculoDePrestacao());
//			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
//			
//			//prazo-=car;
//			this.dashboardModel.setPrazo(this.dashboardModel.getPrazo()-car);
//			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
//				calculo = new CalculoModel();
//				if((i+1) > car) {
////					prazo-=car;
////					calculo = new CalculoModel();
//					calculoModel.setJuro(this.getCalculoJurosGaus(i, calculoModel.getValorEmprestFinac()));
//					calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
//					calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
//					calculo.setPrestacao(calculoModel.getPrestacao());
//					calculo.setJuro(calculoModel.getJuro());
//					calculo.setAmortizacao(calculoModel.getAmortizacao());
//					calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
//					calculo.setDataVencimento(SistemaController.listaDataVencimento(
//							SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
//							this.dashboardModel.getPrazo()).get(i).getDataVencimento());
//
//					aux = new Auxilio();
//					aux.setDoubleAux(0.00001);
//					aux.setIntAux(0);
//					calculo.setAuxilio(aux);
//				}else {
//					
////					calculo = new CalculoModel();
////					calculo.setPrestacao(0);
//					
//					calculoModel.setJuro(this.getCalculoJurosGaus(i, calculoModel.getValorEmprestFinac()));
//					calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
//					calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
//					calculo.setPrestacao(0);
//					calculo.setJuro(calculoModel.getJuro());
//					calculo.setAmortizacao(calculoModel.getAmortizacao());
//					calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
//					calculo.setDataVencimento(SistemaController.listaDataVencimento(
//							SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
//							this.dashboardModel.getPrazo()).get(i).getDataVencimento());
//
//					aux = new Auxilio();
//					aux.setDoubleAux(0.00001);
//					aux.setIntAux(0);
//					calculo.setAuxilio(aux);
//				}
//				listaGaus.add(calculo);
//			}
//		}
//		return listaGaus;
//	}

}
