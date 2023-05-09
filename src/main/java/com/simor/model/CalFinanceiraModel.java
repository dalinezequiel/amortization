package com.simor.model;

public class CalFinanceiraModel {
	private double prestacao, taxa, prazo, valorEmprestimo, valorPagar, valorAdicional, valorPago;
	
	public CalFinanceiraModel() {
		super();
	}

	public double getPrestacao() {
		return prestacao;
	}

	public void setPrestacao(double prestacao) {
		this.prestacao = prestacao;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public double getPrazo() {
		return prazo;
	}

	public void setPrazo(double prazo) {
		this.prazo = prazo;
	}

	public double getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(double valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	public double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public double getValorAdicional() {
		return valorAdicional;
	}

	public void setValorAdicional(double valorAdicional) {
		this.valorAdicional = valorAdicional;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
}
