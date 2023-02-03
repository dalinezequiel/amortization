package com.simor.controller;

import java.io.IOException;
import java.util.ArrayList;
import com.simor.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CalGausController {
	private DashboardModel dashboardModel = null;
	private static CalculoModel calculoModel, calculo = null;
	private Auxilio aux = null;
	private static ArrayList<CalculoModel> listaGaus = null;
	

	public CalGausController(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dashboardModel = new DashboardModel();
		calculoModel = new CalculoModel();
		aux = new Auxilio();
		this.dashboardModel = new App(request, response).getDashboardModel();
		calculoModel.setPrestacao(this.getCalculoDePrestacao());	
	}

	public CalGausController() {
		super();
	}

	// DEVOLVE O OBJECTO CALCULO
	public DashboardModel calculoModelObject() {
		return this.dashboardModel;
	}

	// CALCULAR PRESTAÇÃO
	public double getCalculoDePrestacao() {
		aux = new Auxilio(4);
		aux.setIntAux(1);
		aux.setDoubleAux(2);

		// PRIMEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(0,
				dashboardModel.getValorEmprestFinancia() * (this.getTaxaGausCalculado() * dashboardModel.getPrazo())
						+ dashboardModel.getValorEmprestFinancia());

		// SEGUNDO CALCÚLO
		aux.adicionaDoubleAnyArray(1,
				((this.getTaxaGausCalculado() * (dashboardModel.getPrazo() - aux.getIntAux()) / aux.getDoubleAux())
						+ aux.getIntAux()) * dashboardModel.getPrazo());

		// TERCEIRO CALCÚLO
		aux.adicionaDoubleAnyArray(2, (aux.getDoubleAnyArray()[0] / aux.getDoubleAnyArray()[1]));

		calculoModel.setPrestacao(aux.getDoubleAnyArray()[2]);
		return calculoModel.getPrestacao();
	}

	// PEGAR VALORES INFORMADOS NA PAGINA
//	public DashboardModel getCalculoModelData(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		this.dashboardModel.setValorEmprestFinancia(
//				Double.parseDouble(SistemaController.clean(SistemaController.isNullOrEmpty(request.getParameter("emprest_financia").trim()))));
//		calculoModel.setValorEmprestFinac(dashboardModel.getValorEmprestFinancia());
//		this.dashboardModel
//				.setTaxa(Double.parseDouble(SistemaController.isNullOrEmpty(request.getParameter("taxa").trim())));
//		calculoModel.setJuroInicial(dashboardModel.getTaxa());
//		this.dashboardModel.setTaxaMensal(
//				Double.parseDouble(SistemaController.clean(SistemaController.isNullOrEmpty(request.getParameter("taxa_mensal").trim()))));
//		this.dashboardModel.setTaxaAnual(
//				Double.parseDouble(SistemaController.clean(SistemaController.isNullOrEmpty(request.getParameter("taxa_anual").trim()))));
//		this.dashboardModel
//				.setPrazo(Integer.parseInt(SistemaController.isNullOrEmpty(request.getParameter("prazo").trim())));
//		this.dashboardModel.setDataPrimeiraParcela(Date.valueOf(request.getParameter("ultima_parcela").trim()));
//		return this.dashboardModel;
//	}

	// CALCULAR PRECENTUAL BRUTO
	public double getTaxaGausCalculado() {
		return this.dashboardModel.getTaxa() / 100;
	}

	public double getCalculoJurosGaus(int index, double v) {
	aux = new Auxilio(4);
	aux.setIntAux(1);
	aux.setDoubleAux(2);
	dashboardModel.setValorEmprestFinancia(v);
	//CALCULO DO INDICE PONDERADO
	aux.adicionaDoubleAnyArray(0, (calculoModel.getPrestacao() * (dashboardModel.getPrazo() - index)) - dashboardModel.getValorEmprestFinancia());
	aux.adicionaDoubleAnyArray(1, (aux.getIntAux() + (dashboardModel.getPrazo() - index)) * ((dashboardModel.getPrazo() - index) / aux.getDoubleAux()));
	aux.adicionaDoubleAnyArray(2, (aux.getDoubleAnyArray()[0] / aux.getDoubleAnyArray()[1]));
	
	return aux.getDoubleAnyArray()[2] * (dashboardModel.getPrazo() - index);
}

	// ARRAYLIST DO RESULTADOS DO CALCULO
	public ArrayList<CalculoModel> listaCalGausModel() {
		listaGaus = new ArrayList<CalculoModel>();
		if (this.dashboardModel != null) {
			calculoModel.setValorEmprestFinac(this.dashboardModel.getValorEmprestFinancia());
			calculoModel.setPrestacao(this.getCalculoDePrestacao());
			calculoModel.setDataVencimento(this.dashboardModel.getDataPrimeiraParcela());

			for (int i = 0; i < this.dashboardModel.getPrazo(); i++) {
				calculo = new CalculoModel();
				calculoModel.setJuro(this.getCalculoJurosGaus(i, calculoModel.getValorEmprestFinac()));
				calculoModel.setAmortizacao(calculoModel.getPrestacao() - calculoModel.getJuro());
				calculoModel.setValorEmprestFinac(calculoModel.getValorEmprestFinac() - calculoModel.getAmortizacao());
				calculo.setPrestacao(calculoModel.getPrestacao());
				calculo.setJuro(calculoModel.getJuro());
				calculo.setAmortizacao(calculoModel.getAmortizacao());
				calculo.setValorEmprestFinac(calculoModel.getValorEmprestFinac());
				calculo.setDataVencimento(calculoModel.getDataVencimento());
				
				aux = new Auxilio();
				aux.setDoubleAux(0.00001);
				aux.setIntAux(0);
				calculo.setAuxilio(aux);
				listaGaus.add(calculo);
			}
		}
		return listaGaus;
	}
}
