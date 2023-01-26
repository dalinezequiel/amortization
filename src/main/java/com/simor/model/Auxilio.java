package com.simor.model;

import java.sql.Date;

public class Auxilio {
	// PRIMITIVOS
	private int intAux;
	private int tamanho;
	private double doubleAux;
	private Date data;

	// ARRAYS
	private int[] intAnyArray;
	private double[] doubleAnyArray;

	public Auxilio() {
		super();
	}

	public Auxilio(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.doubleAnyArray = new double[this.tamanho];
	}

	// ADICIONAR VALORES NO ARRAY
	public int adicionaDoubleAnyArray(int index, double value) {
		if ((index < this.tamanho) && (index >= 0)) {
			doubleAnyArray[index] = value;
		} else {
			return -1;
		}
		return index;
	}

	public int getIntAux() {
		return intAux;
	}

	public void setIntAux(int intAux) {
		this.intAux = intAux;
	}

	public double getDoubleAux() {
		return doubleAux;
	}

	public void setDoubleAux(double doubleAux) {
		this.doubleAux = doubleAux;
	}

	public int[] getIntAnyArray() {
		return intAnyArray;
	}

	public void setIntAnyArray(int[] intAnyArray) {
		this.intAnyArray = intAnyArray;
	}

	public double[] getDoubleAnyArray() {
		return doubleAnyArray;
	}

	public void setDoubleAnyArray(double[] doubleAnyArray) {
		this.doubleAnyArray = doubleAnyArray;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
