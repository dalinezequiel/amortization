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

	// REQUEST & RESPONSE
	/*public HomeModel requestHomeModel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.homeModelGlobal
				.setValorEmprestFinancia(this.isNullOrEmpty(request.getParameter("emprest_financia").trim()));
		this.homeModelGlobal.setTaxa(Double.parseDouble(this.isNullOrEmpty(request.getParameter("taxa").trim())));
		this.homeModelGlobal
				.setTaxaMensal(Double.parseDouble(this.isNullOrEmpty(request.getParameter("taxa_mensal").trim())));
		this.homeModelGlobal
				.setTaxaAnual(Double.parseDouble(this.isNullOrEmpty(request.getParameter("taxa_anual").trim())));
		this.homeModelGlobal.setPrazo(Integer.parseInt(this.isNullOrEmpty(request.getParameter("prazo").trim())));

		return this.homeModelGlobal;
	}*/

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		calculo = new HomeCalculoModel();
		calculo.setValorPresenteOuActual(Double.parseDouble(this.homeModelGlobal.getValorEmprestFinancia()));
		calculo.setTaxa(this.homeModelGlobal.getTaxa());
		calculo.setNumeroPrestacao(this.homeModelGlobal.getPrazo());

		aux = new Auxilio(4);
		aux.setIntAux(1);

		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0, aux.getIntAux() + this.getPercentual(calculo.getTaxa()));

		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1, Math.pow(aux.getDoubleAnyArray()[0], calculo.getNumeroPrestacao()));

		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, aux.getDoubleAnyArray()[1] * this.getPercentual(calculo.getTaxa())
				/ (aux.getDoubleAnyArray()[1] - aux.getIntAux()));

		// QUARTO CALCÚLO
		aux.adicionaDoubleAnyArray(3, calculo.getValorPresenteOuActual() * aux.getDoubleAnyArray()[2]);

		calculo.setPrestacao(aux.getDoubleAnyArray()[3]);
		return calculo.getPrestacao();
	}

	// CALCULAR JUROS
	public double getCalculoDeJuro() {
		this.getCalculoDePrestacao();
		return this.getPercentual(calculo.getTaxa()) * calculo.getValorPresenteOuActual();
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
	private double getPercentual(double valor) {
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

	public static void main(String[] args) {
		HomeModel m = new HomeModel();
		m.setValorEmprestFinancia("50000");
		m.setPrazo(12);
		m.setTaxa(1);

		Home hm = new Home();
		System.out.println(hm.isNullOrEmpty(""));
		System.out.println(HomeCalculoController.mascaraMoeda(34678));

		/*
		 * HomeCalculoController hc = new HomeCalculoController(m);
		 * System.out.println(hc.formateMoeda(hc.getCalculoDePrestacao()));
		 * System.out.println(hc.formateMoeda(hc.getCalculoDeJuro()));
		 * System.out.println(hc.formateMoeda(hc.getCalculoDeAmortizacao()));
		 * System.out.println(hc.formateMoeda(hc.getCalculoDeSaldoDevedor()));
		 */
	}
}
