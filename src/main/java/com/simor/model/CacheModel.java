package com.simor.model;

public class CacheModel extends HomeCalculoModel {
	private int idCache;
	private String email;
	private double prestacao;
	private double juro;
	private double amortizacao;
	private double saldoDevedor;

	public CacheModel() {
		super();
	}

	public CacheModel(int idCache, String email, double prestacao, double juro, double amortizacao,
			double saldoDevedor) {
		super();
		this.idCache = idCache;
		this.email = email;
		this.prestacao = prestacao;
		this.juro = juro;
		this.amortizacao = amortizacao;
		this.saldoDevedor = saldoDevedor;
	}

	public int getIdCache() {
		return idCache;
	}

	public void setIdCache(int idCache) {
		this.idCache = idCache;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPrestacao() {
		return prestacao;
	}

	public void setPrestacao(double prestacao) {
		this.prestacao = prestacao;
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
