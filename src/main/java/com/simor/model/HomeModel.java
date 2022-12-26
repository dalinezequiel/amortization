package com.simor.model;

import java.sql.Date;

public class HomeModel {
	private String valorEmprestFinancia;
	private double taxa;
	private double taxaMensal;
	private double taxaAnual;
	private int prazo;
	private String indiceActualizacao;
	private int carencia;
	private String sistemaAmortizacao;
	private Date dataContratacao;
	private Date dataPrimeiraParcela;
	private String tipoBalao;
	private int periodicidadeBalao;
	private int quantBalao;
	private double valorBalao;

	public HomeModel() {
		super();
	}

	public HomeModel(String valorEmprestFinancia, double taxa, double taxaMensal, double taxaAnual, int prazo,
			String indiceActualizacao, int carencia, String sistemaAmortizacao, Date dataContratacao,
			Date dataPrimeiraParcela, String tipoBalao, int periodicidadeBalao, int quantBalao, double valorBalao) {
		super();
		this.valorEmprestFinancia = valorEmprestFinancia;
		this.taxa = taxa;
		this.taxaMensal = taxaMensal;
		this.taxaAnual = taxaAnual;
		this.prazo = prazo;
		this.indiceActualizacao = indiceActualizacao;
		this.carencia = carencia;
		this.sistemaAmortizacao = sistemaAmortizacao;
		this.dataContratacao = dataContratacao;
		this.dataPrimeiraParcela = dataPrimeiraParcela;
		this.tipoBalao = tipoBalao;
		this.periodicidadeBalao = periodicidadeBalao;
		this.quantBalao = quantBalao;
		this.valorBalao = valorBalao;
	}

	public String getValorEmprestFinancia() {
		return valorEmprestFinancia;
	}

	public void setValorEmprestFinancia(String valorEmprestFinancia) {
		this.valorEmprestFinancia = valorEmprestFinancia;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public double getTaxaMensal() {
		return taxaMensal;
	}

	public void setTaxaMensal(double taxaMensal) {
		this.taxaMensal = taxaMensal;
	}

	public double getTaxaAnual() {
		return taxaAnual;
	}

	public void setTaxaAnual(double taxaAnual) {
		this.taxaAnual = taxaAnual;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public String getIndiceActualizacao() {
		return indiceActualizacao;
	}

	public void setIndiceActualizacao(String indiceActualizacao) {
		this.indiceActualizacao = indiceActualizacao;
	}

	public int getCarencia() {
		return carencia;
	}

	public void setCarencia(int carencia) {
		this.carencia = carencia;
	}

	public String getSistemaAmortizacao() {
		return sistemaAmortizacao;
	}

	public void setSistemaAmortizacao(String sistemaAmortizacao) {
		this.sistemaAmortizacao = sistemaAmortizacao;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Date getDataPrimeiraParcela() {
		return dataPrimeiraParcela;
	}

	public void setDataPrimeiraParcela(Date dataPrimeiraParcela) {
		this.dataPrimeiraParcela = dataPrimeiraParcela;
	}

	public String getTipoBalao() {
		return tipoBalao;
	}

	public void setTipoBalao(String tipoBalao) {
		this.tipoBalao = tipoBalao;
	}

	public int getPeriodicidadeBalao() {
		return periodicidadeBalao;
	}

	public void setPeriodicidadeBalao(int periodicidadeBalao) {
		this.periodicidadeBalao = periodicidadeBalao;
	}

	public int getQuantBalao() {
		return quantBalao;
	}

	public void setQuantBalao(int quantBalao) {
		this.quantBalao = quantBalao;
	}

	public double getValorBalao() {
		return valorBalao;
	}

	public void setValorBalao(double valorBalao) {
		this.valorBalao = valorBalao;
	}
}
