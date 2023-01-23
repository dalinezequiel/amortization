package com.simor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.simor.model.ContaModel;

public class LoginDAO {
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	private static ContaModel contModel = null;
	
	public LoginDAO() {
		super();
	}

	public LoginDAO(ContaModel contac) {
		contModel = new ContaModel();
		contModel = contac;
	}

	// LOGIN
	public static boolean login() {
		try {
			String SQL_SELECT_QUERY = "select * from conta where (usuario = ? or email = ?) and senha = ?;";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			pst.setString(1, contModel.getUsuario());
			pst.setString(2, contModel.getEmail());
			pst.setString(3, contModel.getSenha());
			rs = pst.executeQuery();

			while (rs.next()) {
				contModel = new ContaModel();
				contModel.setIdConta(rs.getInt("id_conta"));
				contModel.setEmail(rs.getString("email"));
				contModel.setPerfil(rs.getString("perfil"));
				contModel.setUsuario(rs.getString("usuario"));
				contModel.setSenha(rs.getString("senha"));
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) {
		ContaModel c = new ContaModel();
		c.setIdConta(3);
		c.setEmail("teste@dalinacademy.com");
		c.setPerfil("Administrador");
		c.setUsuario("fatima.mate");
		c.setSenha("fafa,2023");
		//System.out.println(ConnectionDAO.getConnection());
		//System.out.println(ContaDAO.insertConta(c));
		new LoginDAO(c);
		System.out.println("Logado? " + LoginDAO.login());
	}
}
