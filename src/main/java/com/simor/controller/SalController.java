package com.simor.controller;

import java.util.ArrayList;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

public class SalController {
	private final float num;
	private final float taxa;
	private float mesPrestacao;
	private ArrayList<Double> list = null;

	public SalController(float taxa, int prest) {
		this.num = 1;
		this.taxa = taxa;
		this.mesPrestacao = prest;
	}

	// FROMULA: 1/(1+NI)
	protected double model(float prestacao) {
		return this.num / (this.num + (prestacao * this.taxa));
	}

	// LISTA DOS CALCULOS: 1/(1+NI)
	protected ArrayList<Double> initial() {
		list = new ArrayList<Double>();
		for (int i = 0; i < this.mesPrestacao; i++) {
			list.add(this.model(i + this.num));
		}
		return list;
	}

	// SOMAR TODOS VALORES DA LISTA DOS CALCULOS: 1/(1+NI)
	protected double sum() {
		double sum = 0;
		list = this.initial();
		for (int j = 0; j < list.size(); j++) {
			sum += list.get(j).doubleValue();
		}
		return sum;
	}

	// CALCULAR: S(1/(1+NI))-¹
	protected double division() {
		return this.num / this.sum();
	}

	// CALCULO DE FATOR
	protected double factor() {
		this.mesPrestacao = 12;
		this.initial();
		this.sum();
		return this.division();
	}
//
//	public static void main(String[] args) {
//		SalController sal = new SalController(0.01f, 3);
//		System.out.println("test for list: " + sal.initial());
//		System.out.println("test for list sum: " + sal.sum());
//		System.out.println("test sum division: " + sal.division());
//		System.out.println("test sum factor: " + sal.factor());
//	}
}
