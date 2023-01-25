package com.simor.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import com.simor.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalGausController {
	private CalculoModel calculoModel = null;
	private static CalculoModel1 calculoModel1, calculo1 = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel1> listaGaus = null;

	public CalGausController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		calculoModel = new CalculoModel();
		calculoModel1 = new CalculoModel1();
		aux = new Auxilio();
		this.calculoModel = getCalculoModelData(request, response);
	}

	public CalGausController() {
		super();
	}

	// DEVOLVE O OBJECTO CALCULO
	public CalculoModel calculoModelObject() {
		return this.calculoModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		aux.setIntAux(1);
		aux.setDoubleAux(2);

		/*calculoModel.setValorEmprestFinancia(50000);
		calculoModel.setTaxa(1.0);
		calculoModel.setPrazo(12);*/

		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0,
				calculoModel.getValorEmprestFinancia()
						* (this.getPercentualDivisaoPor100(calculoModel.getTaxa()) * calculoModel.getPrazo())
						+ calculoModel.getValorEmprestFinancia());

		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1,
				((this.getPercentualDivisaoPor100(calculoModel.getTaxa()) * (calculoModel.getPrazo() - aux.getIntAux())
						/ aux.getDoubleAux()) + aux.getIntAux()) * calculoModel.getPrazo());

		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, (aux.getDoubleAnyArray()[0] / aux.getDoubleAnyArray()[1]));

		calculoModel1.setPrestacao(aux.getDoubleAnyArray()[2]);
		return calculoModel1.getPrestacao();
	}

	// PEGAR VALORES INFORMADOS NA PAGINA
	private CalculoModel getCalculoModelData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.calculoModel.setValorEmprestFinancia(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("emprest_financia").trim())));
		calculoModel1.setValorEmprestFinac(calculoModel.getValorEmprestFinancia());
		this.calculoModel
				.setTaxa(Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa").trim())));
		calculoModel1.setJuroInicial(calculoModel.getTaxa());
		this.calculoModel.setTaxaMensal(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_mensal").trim())));
		this.calculoModel.setTaxaAnual(
				Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa_anual").trim())));
		this.calculoModel
				.setPrazo(Integer.parseInt(SistemaController.isNullOrEmpty(request.getParameter("prazo").trim())));
		this.calculoModel.setDataPrimeiraParcela(Date.valueOf(request.getParameter("ultima_parcela").trim()));
		aux = new Auxilio();
		aux.setDoubleAux(0.01);
		this.calculoModel.setAuxilioModel(aux);
		return this.calculoModel;
	}

	// CALCULAR PRECENTUAL
	private double getPercentualDivisaoPor100(double juroInicial) {
		return juroInicial / 100;
	}
	
	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaPriceCalculado() {
		return this.calculoModel.getTaxa() / 100;
	}
	
	// ARRAYLIST DO RESULTADOS DO CALCULO
		public ArrayList<CalculoModel1> listaCalGausModel() {
			listaGaus = new ArrayList<CalculoModel1>();
			if (this.calculoModel != null) {
				calculoModel1.setValorEmprestFinac(this.calculoModel.getValorEmprestFinancia());
				calculoModel1.setPrestacao(this.getCalculoDePrestacao());
				calculoModel1.setJuroInicial(this.getTaxaPriceCalculado());

				for (int i = 0; i < this.calculoModel.getPrazo(); i++) {
					calculo1 = new CalculoModel1();
					calculoModel1.setJuro(calculoModel1.getJuroInicial() * calculoModel1.getValorEmprestFinac());
					calculoModel1.setAmortizacao(calculoModel1.getPrestacao() - calculoModel1.getJuro());
					calculoModel1
							.setValorEmprestFinac(calculoModel1.getValorEmprestFinac() - calculoModel1.getAmortizacao());
					calculo1.setPrestacao(calculoModel1.getPrestacao());
					calculo1.setJuro(calculoModel1.getJuro());
					calculo1.setAmortizacao(calculoModel1.getAmortizacao());
					calculo1.setValorEmprestFinac(calculoModel1.getValorEmprestFinac());
					listaGaus.add(calculo1);
				}
			}
			return listaGaus;
		}
	public static void main(String[] args) {
		System.out.println(new CalGausController().getCalculoDePrestacao());
	}
}
