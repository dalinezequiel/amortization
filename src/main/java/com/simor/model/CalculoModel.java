package com.simor.model;

import java.sql.Date;

public class CalculoModel {
	private double valorEmprestFinac;
	private double prestacao;
	private double juro;
	private double juroInicial;
	private double amortizacao;
	private Date dataVencimento;
	private Auxilio auxilio;
	private DashboardModel dashboardModel;

	public double getValorEmprestFinac() {
		return valorEmprestFinac;
	}

	public void setValorEmprestFinac(double valorEmprestFinac) {
		this.valorEmprestFinac = valorEmprestFinac;
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

	public double getJuroInicial() {
		return juroInicial;
	}

	public void setJuroInicial(double juroInicial) {
		this.juroInicial = juroInicial;
	}

	public double getAmortizacao() {
		return amortizacao;
	}

	public void setAmortizacao(double amortizacao) {
		this.amortizacao = amortizacao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Auxilio getAuxilio() {
		return auxilio;
	}

	public void setAuxilio(Auxilio auxilio) {
		this.auxilio = auxilio;
	}

	public DashboardModel getDashboardModel() {
		return dashboardModel;
	}

	public void setDashboardModel(DashboardModel dashboardModel) {
		this.dashboardModel = dashboardModel;
	}
}
