package com.simor.model;

public class ContaModel {
	private int idConta;
	private String email;
	private String perfil;
	private String usuario;
	private String senha;
	private String comfirmaSenha;

	public ContaModel() {
		super();
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getComfirmaSenha() {
		return comfirmaSenha;
	}

	public void setComfirmaSenha(String comfirmaSenha) {
		this.comfirmaSenha = comfirmaSenha;
	}
}
