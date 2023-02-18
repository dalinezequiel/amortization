package com.simor.model;

import java.sql.Date;

public class Auxilio {
	// PRIMITIVOS
	private String stringAux;
	private int intAux;
	private int tamanho;
	private double doubleAux;
	private Date data;

	// ARRAYS
	private String[] stringAnyArray;
	private int[] intAnyArray;
	private double[] doubleAnyArray;

	public Auxilio() {
		super();
	}

	public Auxilio(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.doubleAnyArray = new double[this.tamanho];
		this.intAnyArray = new int[this.tamanho];
	}

	// ADICIONAR VALORES DOUBLE NO ARRAY
	public int adicionaDoubleAnyArray(int index, double value) {
		if ((index < this.tamanho) && (index >= 0)) {
			doubleAnyArray[index] = value;
		} else {
			return -1;
		}
		return index;
	}
	
	// ADICIONAR VALORES INT NO ARRAY
	public int adicionaIntAnyArray(int index, int value) {
		if ((index < this.tamanho) && (index >= 0)) {
			intAnyArray[index] = value;
		} else {
			return -1;
		}
		return index;
	}
	
	public String getStringAux() {
		return stringAux;
	}

	public void setStringAux(String stringAux) {
		this.stringAux = stringAux;
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

	public String[] getStringAnyArray() {
		return stringAnyArray;
	}

	public void setStringAnyArray(String[] stringAnyArray) {
		this.stringAnyArray = stringAnyArray;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
