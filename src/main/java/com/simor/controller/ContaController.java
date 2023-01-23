package com.simor.controller;

import java.util.Random;
import com.simor.model.*;

public class ContaController {
	private static Auxilio aux = null;
	private static Random random = null;

	// GERAR CÓDIGO DE VERIFICAÇÃO INTERNA DO USUÁRIO
	public ContaController() {
		super();
	}

	public static int getUserId() {
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
}
