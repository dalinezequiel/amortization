package com.simor.controller;

import java.util.ArrayList;
import java.util.Random;
import com.simor.model.Auxilio;
import com.simor.model.CalculoModel;

import java.sql.Date;
import java.text.*;
import java.time.LocalDate;

public class SistemaController {
	private static DecimalFormat moeda, numero = null;
	private static Auxilio aux = null;
	private static Random random = null;
	private static CalculoModel calculoModel = null;
	private static ArrayList<CalculoModel> listData = null;

	public SistemaController() {
		super();
		moeda = new DecimalFormat("###,###.00");
		numero = new DecimalFormat("0.0");
	}

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
	
	// GERAR COUNTER
	public static int getCounter(int i) {
		return i + 1;
	}

	// LIMPEZA ANTES DE PREENCHER O VALOR
	public static String isNullOrEmpty(String value) {
		if (value.isEmpty() || value == null) {
			return "0";
		}
		return value;
	}
	
	public static String clean(String value) {
		aux = new Auxilio();
		aux.setStringAux("");
		for(int i=0; i<value.length(); i++) {
			if(String.valueOf(value.charAt(i)).equals(",")){
				aux.setStringAux(aux.getStringAux() + ".");
				System.out.println("Encontrou virgula");
			}
			else if(String.valueOf(value.charAt(i)).equals(".")) {
				aux.setStringAux(aux.getStringAux() + "");
				System.out.println("Encontrou ponto");
			}
			else {
				aux.setStringAux(aux.getStringAux() + value.charAt(i));
				//aux.setStringAux(aux.getStringAux());
			}
		}
		return aux.getStringAux();
	}
	
	public static String cleanM(String value) {
		aux = new Auxilio();
		aux.setStringAux("");
		for(int i=0; i<value.length(); i++) {
			if(String.valueOf(value.charAt(i)).equals(",")){
				aux.setStringAux(aux.getStringAux() + ".");
				System.out.println("Encontrou virgula");
			}
			/*else if(String.valueOf(value.charAt(i)).equals(".")) {
				aux.setStringAux(aux.getStringAux() + "");
				System.out.println("Encontrou ponto"); "31,560.00"
			}*/
			else {
				aux.setStringAux(aux.getStringAux() + value.charAt(i));
				//aux.setStringAux(aux.getStringAux());
			}
		}
		return aux.getStringAux();
	}

	// FORMATAR A DATA INFORMADA
	public static String getFormatedDate(String dat) {
		return new SimpleDateFormat("dd-MM-yyyy").format(Date.valueOf(dat));
	}

	// PEGAR A DATA ACTUAL
	public static String getDataActual() {
		return new SimpleDateFormat("dd-MM-yyyy").format(Date.valueOf(LocalDate.now()));
	}

	// FORMATAR A DUAS CASAS DECIMAIS
	public static String mascaraMoeda(double num) {
		moeda = new DecimalFormat("###,###.00");
		return moeda.format(num);
	}
	
	// FORMATAR A DUAS CASAS DECIMAIS
	public static String maskNum(double num) {
		numero = new DecimalFormat("0.00");
		return numero.format(num);
	}
	
	// DEVEOLVE NULL PARA RESETAR A LISTA
	public static ArrayList<CalculoModel> getReset() {
		return null;
	}
	
	//
	public static ArrayList<CalculoModel> listaDataVencimento(String data, int prazo){
		listData = new ArrayList<CalculoModel>();
		aux = new Auxilio(3);
		aux.setStringAnyArray(data.split("-", 3));
		aux.adicionaIntAnyArray(1, Integer.parseInt(aux.getStringAnyArray()[1]));
		aux.adicionaIntAnyArray(2, Integer.parseInt(aux.getStringAnyArray()[2]));
		for(int i=0; i<prazo; i++) {
			calculoModel = new CalculoModel();
			if(aux.getIntAnyArray()[1]==13) {
				aux.adicionaIntAnyArray(1, 1);
				aux.adicionaIntAnyArray(2, aux.getIntAnyArray()[2] + 1);
			}
			aux.setStringAux(aux.getIntAnyArray()[2]+"-"+String.valueOf(aux.getIntAnyArray()[1])+"-"+aux.getStringAnyArray()[0]);
			calculoModel.setDataVencimento(Date.valueOf(aux.getStringAux()));
			aux.adicionaIntAnyArray(1, aux.getIntAnyArray()[1] + 1);
			listData.add(calculoModel);
		}
		return listData;
	}
	
	public static void main(String [] args) {
		//System.out.print(SistemaController.cleanM("31,560.00"));
		ArrayList<CalculoModel> list = SistemaController.listaDataVencimento("05-10-2023", 7);
		for(int i=0; i<list.size(); i++) {
			System.out.println(SistemaController.getFormatedDate(String.valueOf(list.get(i).getDataVencimento())));
		}
	}
}
