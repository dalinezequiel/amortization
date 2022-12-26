package com.simor.model;

public class HomeCalculoModel {
	private double prestacao;
	private double valorPresenteOuActual;
	private double taxa;
	private int numeroPrestacao;
	private double juro;
	private double amortizacao;
	private double saldoDevedor;
	
	public HomeCalculoModel() {
		super();
	}

	public double getPrestacao() {
		return prestacao;
	}

	public void setPrestacao(double prestacao) {
		this.prestacao = prestacao;
	}

	public double getValorPresenteOuActual() {
		return valorPresenteOuActual;
	}

	public void setValorPresenteOuActual(double valorPresenteOuActual) {
		this.valorPresenteOuActual = valorPresenteOuActual;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public int getNumeroPrestacao() {
		return numeroPrestacao;
	}

	public void setNumeroPrestacao(int numeroPrestacao) {
		this.numeroPrestacao = numeroPrestacao;
	}

	public double getJuro() {
		return juro;
	}

	public void setJuro(double juro) {
		this.juro = juro;
	}

	public double getAmortizacao() {
		return amortizacao;
	}

	public void setAmortizacao(double amortizacao) {
		this.amortizacao = amortizacao;
	}

	public double getSaldoDevedor() {
		return saldoDevedor;
	}

	public void setSaldoDevedor(double saldoDevedor) {
		this.saldoDevedor = saldoDevedor;
	}
}
