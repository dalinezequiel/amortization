package com.simor.controller;

public class AtrasoController {
	public double multa(double taxaAtraso, double valorEmprestFinancia) {
		return this.taxaPercentual(taxaAtraso) * valorEmprestFinancia;
	}

	public double juroAtraso(double taxaMulta, double valorEmprestFinancia) {
		return this.taxaPercentual(taxaMulta) * valorEmprestFinancia;
	}

	private double taxaPercentual(double taxa) {
		return taxa / 100;
	}
}
