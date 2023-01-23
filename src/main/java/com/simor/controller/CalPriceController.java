package com.simor.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.simor.model.*;

public class CalPriceController {
	private CalculoModel calculoModel = null;
	private static CalPriceModel calPriceModel = null;
	
	//private CalPriceModel calculo;
	private Auxilio aux = null;

	public CalPriceController() {
		calculoModel = new CalculoModel();
		calPriceModel = new CalPriceModel();
	}
	
	public CalPriceController(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		calculoModel = new CalculoModel();
		calPriceModel = new CalPriceModel();
		this.calculoModel = getCalculoModelData(request, response);
	}
	
	public CalculoModel calculoModelTeste() {
		return this.calculoModel;
	}
	
	// CALCULAR PRESTAÇÃO
		public double getCalculoDePrestacao(CalculoModel calModel) {
			aux = new Auxilio(4);
			aux.setIntAux(1);

			// PRIMEIRO CALCÚLO
			aux.adicionaDoubleAnyArray(0, aux.getIntAux() + getPercentualPriceCalculado(calModel.getValorEmprestFinancia(),calModel.getTaxa()));

			// SEGUNDO CALCÚLO
			aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], calModel.getPrazo()));

			// TERCEIRO CALCÚLO
			aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[1] * getPercentualPriceCalculado(calModel.getValorEmprestFinancia(),calModel.getTaxa())
					/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));

			// QUARTO CALCÚLO
			aux.adicionaDoubleAnyArray(3, calModel.getValorEmprestFinancia() * aux.getDoubleAnyArray()[2]);

			calPriceModel.setPrestacao(aux.getDoubleAnyArray()[3]);
			return calPriceModel.getPrestacao();
		}

	// PEGAR VALORES INFORMADOS NA PAGINA
	public CalculoModel getCalculoModelData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.calculoModel.setValorEmprestFinancia(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("emprest_financia").trim())));
		calPriceModel.setValorEmprestFinac(calculoModel.getValorEmprestFinancia());
		this.calculoModel
				.setTaxa(Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa").trim())));
		calPriceModel.setJuroInicial(calculoModel.getTaxa());
		this.calculoModel.setTaxaMensal(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_mensal").trim())));
		this.calculoModel.setTaxaAnual(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_anual").trim())));
		this.calculoModel
				.setPrazo(Integer.parseInt(SistemaController.isNullOrEmpty(request.getParameter("prazo").trim())));

		return this.calculoModel;
	}

	// CALCULAR PRECENTUAL
	public static double getPercentualPriceCalculado() {
		return calPriceModel.getValorEmprestFinac() * (calPriceModel.getJuroInicial() / 100);
	}
	public double getPercentualPriceCalculado(double valorEmprestFinanc, double juroInicial) {
		return juroInicial / 100;
	}
}
