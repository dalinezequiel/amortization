package com.simor.controller;

import java.util.*;
import java.text.*;
import com.simor.model.*;

public class SerieNPController {
	private Auxilio aux = null;
	private ArrayList<Integer> list = null;
	private int num, percentagem;
	private float divExpoente;
	private double taxa;

	public SerieNPController(double taxa) {
		aux = new Auxilio();
		this.num = 1;
		this.divExpoente = 30;
		this.percentagem = 100;
		this.taxa = taxa;
	}

	// DEVOLVE TOTAL DE DIAS DO MÊS
	public int getDaysOfMounth(int mes) {
		switch (mes) {
		case 1:
			aux.setIntAux(31);
			break;
		case 2:
			if (this.getYear() % 4 == 0) {
				aux.setIntAux(29);
			} else {
				aux.setIntAux(28);
			}
			break;
		case 3:
			aux.setIntAux(31);
			break;
		case 4:
			aux.setIntAux(30);
			break;
		case 5:
			aux.setIntAux(31);
			break;
		case 6:
			aux.setIntAux(30);
			break;
		case 7:
			aux.setIntAux(31);
			break;
		case 8:
			aux.setIntAux(31);
			break;
		case 9:
			aux.setIntAux(30);
			break;
		case 10:
			aux.setIntAux(31);
			break;
		case 11:
			aux.setIntAux(30);
			break;
		case 12:
			aux.setIntAux(31);
			break;
		default:
			aux.setIntAux(0);
			break;
		}
		return aux.getIntAux();
	}

	// DEVOLVE LISTA DOS DIAS DOS MESES NUM INTERVALO
	public ArrayList<Integer> listaDataAcumulo(int mes) {
		list = new ArrayList<Integer>();
		for (int i = 0; i < mes; i++) {
			list.add(this.getDaysOfMounth(i + 1));
		}
		return list;
	}

	// DEVOLVE DIAS ACUMULADOS
	public int getDiaAcumulado(int mes) {
		list = this.listaDataAcumulo(mes);
		aux.setIntAux(0);
		for (int i = 0; i < list.size(); i++) {
			aux.setIntAux(aux.getIntAux() + list.get(i));
		}
		return aux.getIntAux();
	}

	// DEVOLVE O ANO ACTUAL
	public int getYear() {
		return Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
	}

	// CALCULA FATOR DE ACTUALIZAÇÃO
	public double factorActualizacao(float diaAcumulado) {
		return Math.pow(num / (num + taxa), (diaAcumulado / divExpoente));
	}

	// TAXA MENSAL
	public double taxaMensal(int mes) {
		return (Math.pow((num + taxa), this.getDaysOfMounth(mes) / divExpoente) - num) * percentagem;
	}

	// COEFICIENTE
	public double coeficiente() {
		int mes = 1;
		float coeficiente = 0;
		for (int i = 0; i < 12; i++) {
			coeficiente += this.factorActualizacao(this.getDiaAcumulado(mes));
			mes++;
		}
		return num / coeficiente;
	}
}
