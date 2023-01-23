package com.simor.controller;

import java.util.Random;
import com.simor.model.Auxilio;

import java.sql.Date;
import java.text.*;
import java.time.LocalDate;

public class SistemaController {
	private static Auxilio aux = null;
	private static Random random = null;

	// GERAR CÓDIGO
	public static int getId() {
		aux = new Auxilio();
		random = new Random();
		aux.setIntAux(random.nextInt(9999999));
		if (aux.getIntAux() < 1000000) {
			while (aux.getIntAux() < 1000000) {
				aux.setIntAux(random.nextInt(9999999));
			}
			return aux.getIntAux();
		} else {
			return aux.getIntAux();
		}
	}

	// LIMPEZA ANTES DE PREENCHER O VALOR
	public static String isNullOrEmpty(String value) {
		if (value.isEmpty() || value == null) {
			return "0";
		}
		return value;
	}

	// FORMATAR A DATA INFORMADA
	public static String getFormatedDate(String dat) {
		return new SimpleDateFormat("dd-MM-yyyy").format(Date.valueOf(dat));
	}

	// PEGAR A DATA ACTUAL
	public static String getDataActual() {
		return new SimpleDateFormat("dd-MM-yyyy").format(Date.valueOf(LocalDate.now()));
	}
}
