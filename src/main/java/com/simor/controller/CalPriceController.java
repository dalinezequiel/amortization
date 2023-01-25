package com.simor.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.Date;

import com.simor.model.*;

public class CalPriceController {
	private CalculoModel calculoModel = null;
	private static CalPriceModel calPriceModel, calPrice = null;
	private Auxilio aux = null;
	private static ArrayList<CalPriceModel> listaPrice = null;

	public CalPriceController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		calculoModel = new CalculoModel();
		calPriceModel = new CalPriceModel();
		aux = new Auxilio();
		this.calculoModel = getCalculoModelData(request, response);
	}

	public CalculoModel calculoModelObject() {
		return this.calculoModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		aux.setIntAux(1);

		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux()
				+ getPercentualPriceCalculado(calculoModel.getValorEmprestFinancia(), calculoModel.getTaxa()));

		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], calculoModel.getPrazo()));

		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2,
				aux.getDoubleAnyArray()[1]
						* getPercentualPriceCalculado(calculoModel.getValorEmprestFinancia(), calculoModel.getTaxa())
						/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));

		// QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, calculoModel.getValorEmprestFinancia() * aux.getDoubleAnyArray()[2]);

		calPriceModel.setPrestacao(aux.getDoubleAnyArray()[3]);
		return calPriceModel.getPrestacao();
	}

	// PEGAR VALORES INFORMADOS NA PAGINA
	private CalculoModel getCalculoModelData(HttpServletRequest request, HttpServletResponse response)
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
		this.calculoModel.setDataPrimeiraParcela(Date.valueOf(request.getParameter("ultima_parcela").trim()));
		aux = new Auxilio();
		aux.setDoubleAux(0.01);
		this.calculoModel.setAuxilioModel(aux);
		return this.calculoModel;
	}

	// CALCULAR PRECENTUAL
	private double getPercentualPriceCalculado(double valorEmprestFinanc, double juroInicial) {
		return juroInicial / 100;
	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaPriceCalculado() {
		return this.calculoModel.getTaxa() / 100;
	}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalPriceModel> listaCalPriceModel() {
		listaPrice = new ArrayList<CalPriceModel>();
		if (this.calculoModel != null) {
			calPriceModel.setValorEmprestFinac(this.calculoModel.getValorEmprestFinancia());
			calPriceModel.setPrestacao(this.getCalculoDePrestacao());
			calPriceModel.setJuroInicial(this.getTaxaPriceCalculado());

			for (int i = 0; i < this.calculoModel.getPrazo(); i++) {
				calPrice = new CalPriceModel();
				calPriceModel.setJuro(calPriceModel.getJuroInicial() * calPriceModel.getValorEmprestFinac());
				calPriceModel.setAmortizacao(calPriceModel.getPrestacao() - calPriceModel.getJuro());
				calPriceModel
						.setValorEmprestFinac(calPriceModel.getValorEmprestFinac() - calPriceModel.getAmortizacao());
				calPrice.setPrestacao(calPriceModel.getPrestacao());
				calPrice.setJuro(calPriceModel.getJuro());
				calPrice.setAmortizacao(calPriceModel.getAmortizacao());
				calPrice.setValorEmprestFinac(calPriceModel.getValorEmprestFinac());
				listaPrice.add(calPrice);
			}
		}
		return listaPrice;
	}
}
