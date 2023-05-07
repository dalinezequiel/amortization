package com.simor.controller;

public class CalSacBalao {
	protected double valorBalao;
	protected int periodoBalao;
	protected int prazo;
	
	public CalSacBalao() {
		this.valorBalao=300;
		this.periodoBalao=7;
		this.prazo=12;
	}
	
	public int quantidadadeBalao() {
		return this.prazo/this.periodoBalao;
	}
	
	public static void main(String [] args) {
		CalSacBalao sal=new CalSacBalao();
		System.out.println(sal.quantidadadeBalao());
	}
}
