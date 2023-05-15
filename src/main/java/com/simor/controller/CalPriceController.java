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
	private double[] balaoArray = null;
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
	
	public CalPriceController() {
		dashboardModel = new DashboardModel();
		dashboardModel.setTaxa(1);
		calculoModel = new CalculoModel();
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

	public double getCalculoDePrestacao(int prazo) {
		aux = new Auxilio(4);
		aux.setIntAux(1);
		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux()
				+ getPercentualPriceCalculado(dashboardModel.getValorEmprestFinancia(), dashboardModel.getTaxa()));
		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], prazo));
		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[1]
				* getPercentualPriceCalculado(dashboardModel.getValorEmprestFinancia(), dashboardModel.getTaxa())
				/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));
		// QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, dashboardModel.getValorEmprestFinancia() * aux.getDoubleAnyArray()[2]);
		calculoModel.setPrestacao(aux.getDoubleAnyArray()[3]);
		return calculoModel.getPrestacao();
	}

	public double getCalculoDePrestacao(int prazo, double valor) {
		aux = new Auxilio(4);
		aux.setIntAux(1);
		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux() + getPercentualPriceCalculado(valor, dashboardModel.getTaxa()));
		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], prazo));
		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2,
				aux.getDoubleAnyArray()[1] * getPercentualPriceCalculado(valor, dashboardModel.getTaxa())
						/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));

		// QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, valor * aux.getDoubleAnyArray()[2]);
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
	public ArrayList<CalculoModel> listaCalPriceModel() {
		listaPrice = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
//			calculoModel.setPrestacao(0);
			calculoModel.setJuroInicial(this.getTaxaPriceCalculado());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());
			
			
			calculoModel.setPrestacao(this.getCalculoDePrestacao());
			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
				calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
				calculoModel
						.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getPrestacao());
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
				listaPrice.add(calculo);
			}
//			if (this.dashboardModel.getCarencia() > 0) {
//				double somaJuro = 0;
//				if (this.dashboardModel.getTipoCarencia().equals("Sem juros")) {
//					for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
//						if (i <= this.dashboardModel.getCarencia()) {
//							somaJuro += calculoModel.getJuro();
//						}
//						if ((i + 1) > this.dashboardModel.getCarencia()) {
//							calculoModel.setPrestacao(this.getCalculoDePrestacao(
//									this.dashboardModel.getPrazo() - this.dashboardModel.getCarencia(),
//									this.dashboardModel.getValorEmprestFinancia() + somaJuro));
//						}
//
//						calculo = new CalculoModel();
//						calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
//						calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
//						calculoModel.setValorEmprestFinac(
//								calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
//						calculo.setPrestacao(calculoModel.getPrestacao());
//						calculo.setJuro(calculoModel.getJuro());
//						calculo.setAmortizacao(calculoModel.getAmortizacao());
//						calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
//						calculo.setDataVencimento(SistemaController.listaDataVencimento(
//								SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
//								this.dashboardModel.getPrazo()).get(i).getDataVencimento());
//						aux = new Auxilio();
//						aux.setDoubleAux(0.00001);
//						aux.setIntAux(0);
//						calculo.setAuxilio(aux);
//						listaPrice.add(calculo);
//					}
//				} else if (this.dashboardModel.getTipoCarencia().equals("Com juros")) {
//
//					for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
//						calculo = new CalculoModel();
//						calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
//						if ((i + 1) > this.dashboardModel.getCarencia()) {
//							calculoModel.setPrestacao(this.getCalculoDePrestacao(
//									this.dashboardModel.getPrazo() - this.dashboardModel.getCarencia()));
//							calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
//						}
//						if (i <= this.dashboardModel.getCarencia()) {
//							calculoModel.setPrestacao(calculoModel.getJuro() + calculoModel.getAmortizacao());
//						}
//						calculoModel.setValorEmprestFinac(
//								calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
//						calculo.setPrestacao(calculoModel.getPrestacao());
//						calculo.setJuro(calculoModel.getJuro());
//						calculo.setAmortizacao(calculoModel.getAmortizacao());
//						calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
//						calculo.setDataVencimento(SistemaController.listaDataVencimento(
//								SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
//								this.dashboardModel.getPrazo()).get(i).getDataVencimento());
//						aux = new Auxilio();
//						aux.setDoubleAux(0.00001);
//						aux.setIntAux(0);
//						calculo.setAuxilio(aux);
//						listaPrice.add(calculo);
//					}
//				}
//			} else {
//
//				calculoModel.setPrestacao(this.getCalculoDePrestacao());
//				for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
//					calculo = new CalculoModel();
//					calculoModel.setJuro(calculoModel.getJuroInicial() * calculoModel.getValorEmprestFinac());
//					calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
//					calculoModel
//							.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
//					calculo.setPrestacao(calculoModel.getPrestacao());
//					calculo.setJuro(calculoModel.getJuro());
//					calculo.setAmortizacao(calculoModel.getAmortizacao());
//					calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
//					calculo.setDataVencimento(SistemaController.listaDataVencimento(
//							SistemaController.getFormatedDate(String.valueOf(calculoModel.getDataVencimento())),
//							this.dashboardModel.getPrazo()).get(i).getDataVencimento());
//					aux = new Auxilio();
//					aux.setDoubleAux(0.00001);
//					aux.setIntAux(0);
//					calculo.setAuxilio(aux);
//					listaPrice.add(calculo);
//				}
//			}
		}
		return listaPrice;
	}
	
	// CALCULAR BALAO
		public double[] listaBalao() {
			balaoArray = new double[this.dashboardModel.getPrazo()];
			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				if (this.dashboardModel.getPeriodicidadeBalao() != 0) {
					if (((i + 1) % this.dashboardModel.getPeriodicidadeBalao() == 0)) {/*&& (i != 0)*/
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
		
		public static void main(String [] args) {
			CalPriceController pr=new CalPriceController();
			System.out.println("Prestacao: "+pr.getCalculoDePrestacao(12, 49800));
		}
}
