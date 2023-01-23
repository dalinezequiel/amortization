package com.simor.controller;

import java.text.DecimalFormat;
import com.simor.model.*;
import com.simor.servelet.Home;

public class HomeCalculoController {
	private static DecimalFormat moeda = null;

	private HomeCalculoModel calculo;
	private HomeModel homeModelGlobal;
	private Auxilio aux = null;

	public HomeCalculoController() {
		super();
		moeda = new DecimalFormat("###,###.00");
		aux = new Auxilio();
	}

	public HomeCalculoController(HomeModel homeModel) {
		super();
		this.homeModelGlobal = homeModel;
		aux = new Auxilio();
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		calculo = new HomeCalculoModel();
		calculo.setValorPresenteOuActual(Double.parseDouble(this.homeModelGlobal.getValorEmprestFinancia()));
		calculo.setTaxa(this.homeModelGlobal.getTaxa());
		calculo.setNumeroPrestacao(this.homeModelGlobal.getPrazo());

		aux = new Auxilio(4);
		aux.setIntAux(1);

		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux() + getPercentual(calculo.getTaxa()));

		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], calculo.getNumeroPrestacao()));

		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[1] * getPercentual(calculo.getTaxa())
				/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));

		// QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, calculo.getValorPresenteOuActual() * aux.getDoubleAnyArray()[2]);

		calculo.setPrestacao(aux.getDoubleAnyArray()[3]);
		return calculo.getPrestacao();
	}

	// CALCULAR JUROS
	public double getCalculoDeJuro() {
		this.getCalculoDePrestacao();
		return getPercentual(calculo.getTaxa()) * calculo.getValorPresenteOuActual();
	}

	// CALCULAR AMORTIZAÇÃO
	public double getCalculoDeAmortizacao() {
		this.getCalculoDePrestacao();
		return this.getCalculoDePrestacao() - this.getCalculoDeJuro();
	}

	// CALCULAR SALDO DEVEDOR
	public double getCalculoDeSaldoDevedor() {
		this.getCalculoDePrestacao();
		return this.calculo.getValorPresenteOuActual() - this.getCalculoDeAmortizacao();
	}

	// CALCULAR PRECENTUAL
	public static double getPercentual(double valor) {
		return (valor / 100);
	}

	// FORMATAR A DUAS CASAS DECIMAIS
	public static String mascaraMoeda(double num) {
		moeda = new DecimalFormat("###,###.00");
		return moeda.format(num);
	}

	// VALIADAÇÃO
	public String isNullOrEmpty(String value) {
		if (value.isEmpty() || value == null) {
			return "0";
		}
		return value;
	}
}
