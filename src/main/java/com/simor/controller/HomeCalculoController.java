package com.simor.controller;

import java.text.DecimalFormat;
import com.simor.model.*;

public class HomeCalculoController {
	private DecimalFormat moeda = null;
	
	private HomeCalculoModel calculo;
	private HomeModel homeModel;
	private Auxilio aux = null;

	public HomeCalculoController() {
		super();
		moeda = new DecimalFormat("###,###.00");
		aux = new Auxilio();
	}
	
	public HomeCalculoController(HomeModel homeModel) {
		super();
		this.homeModel = homeModel;
		aux = new Auxilio();
		moeda = new DecimalFormat("###,###.00");
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux.setIntAux(1);
		calculo = new HomeCalculoModel();
		calculo.setValorPresenteOuActual(Double.parseDouble(homeModel.getValorEmprestFinancia()));
		calculo.setTaxa(homeModel.getTaxa());
		calculo.setNumeroPrestacao(homeModel.getPrazo());
		
		aux = new Auxilio(4);
		//PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux() + this.getPercentual(calculo.getTaxa()));
		
		//SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], calculo.getNumeroPrestacao()));
		
		//TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[1] * this.getPercentual(calculo.getTaxa()) / (aux.getDoubleAnyArray()[1] - aux.getIntAux()));
		
		//QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, calculo.getValorPresenteOuActual() * aux.getDoubleAnyArray()[2]);
		
		calculo.setPrestacao(aux.getDoubleAnyArray()[3]);
		
		/*double c2 = aux.getIntAux() + this.getPercentual(calculo.getTaxa()); // correcto
		double c22 = Math.pow(c2, calculo.getNumeroPrestacao()); // correcto

		double c3 = (c22 * this.getPercentual(calculo.getTaxa())) / (c22 - aux.getIntAux());
		double c1 = calculo.getValorPresenteOuActual() * c3;

		calculo.setPrestacao(c1);*/
		return this.arredondaDuasCasasDecimal(calculo.getPrestacao());
	}

	// CALCULAR JUROS
	public double getCalculoDeJuro() {
		this.getCalculoDePrestacao();
		return this.arredondaDuasCasasDecimal2(this.getPercentual(calculo.getTaxa()) * calculo.getValorPresenteOuActual());
	}

	// CALCULAR AMORTIZAÇÃO
	public double getCalculoDeAmortizacao() {
		this.getCalculoDePrestacao();
		return this.arredondaDuasCasasDecimal(this.getCalculoDePrestacao() - this.getCalculoDeJuro());
	}

	// CALCULAR SALDO DEVEDOR
	public double getCalculoDeSaldoDevedor() {
		this.getCalculoDePrestacao();
		return this.arredondaDuasCasasDecimal(calculo.getValorPresenteOuActual() - this.getCalculoDeAmortizacao());
	}

	// CALCULAR PRECENTUAL
	private double getPercentual(double valor) {
		return (valor / 100);
	}

	// ARRENDONADR A DUAS CASAS DECIMAIS
	private double arredondaDuasCasasDecimal(double valor) {
		return Math.round(valor * 100) / 100.0;
	}

	// ARRENDONADR A DUAS CASAS DECIMAIS
	private double arredondaDuasCasasDecimal2(double valor) {
		return Math.round(valor * 100) / 100.0;
	}
	
	private String getFormat(double num) {
		return moeda.format(num);
	}

	public static void main(String[] args) {
		/*System.out.println(new HomeCalculoController().calcularPrestacao());
		System.out.println(new HomeCalculoController().calcularJuro());
		System.out.println(new HomeCalculoController().calcularAmortizacao());
		System.out.println(new HomeCalculoController().calcularSaldoDevedor());
		System.out.println(new HomeCalculoController().getFormat(5000000));*/
		HomeModel m = new HomeModel();
		m.setValorEmprestFinancia("50000");
		m.setPrazo(12);
		m.setTaxa(1);
		
		HomeCalculoController hc = new HomeCalculoController(m);
		System.out.println(hc.getFormat(hc.getCalculoDePrestacao()));
	}
}
