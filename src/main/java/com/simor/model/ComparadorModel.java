package com.simor.model;

import java.util.ArrayList;

public class ComparadorModel {
	private String sistema;
	private double primeiraParcela;
	private double ultimaParcela;
	private double totalJuro;
	private double totalPago;
	private ComparadorModel comparador;
	private ArrayList<ComparadorModel> listComparador;

	public ComparadorModel() {
		super();
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public double getPrimeiraParcela() {
		return primeiraParcela;
	}

	public void setPrimeiraParcela(double primeiraParcela) {
		this.primeiraParcela = primeiraParcela;
	}

	public double getUltimaParcela() {
		return ultimaParcela;
	}

	public void setUltimaParcela(double ultimaParcela) {
		this.ultimaParcela = ultimaParcela;
	}

	public double getTotalJuro() {
		return totalJuro;
	}

	public void setTotalJuro(double totalJuro) {
		this.totalJuro = totalJuro;
	}

	public double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}

	public ComparadorModel getComparador() {
		return comparador;
	}

	public void setComparador(ComparadorModel comparador) {
		this.comparador = comparador;
	}

	public ArrayList<ComparadorModel> getListComparador() {
		return listComparador;
	}

	public void setListComparador(ArrayList<ComparadorModel> listComparador) {
		this.listComparador = listComparador;
	}
}
