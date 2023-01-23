package com.simor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.simor.controller.ContaController;
import com.simor.model.ContaModel;

public class ContaDAO {
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	private static ArrayList<ContaModel> listaCont = null;
	private static ContaModel contModel = null;

	// CRIAR CREDÊNCIAIS DO USUÁRIO
	public static boolean insertConta(ContaModel conta) {
		try {
			String SQL_INSERT_QUERY = "INSERT INTO conta(id_conta, email, perfil, usuario, senha) values(?,?,?,?,?)";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_INSERT_QUERY);

			pst.setInt(1, conta.getIdConta());
			pst.setString(2, conta.getEmail());
			pst.setString(3, conta.getPerfil());
			pst.setString(4, conta.getUsuario());
			pst.setString(5, conta.getSenha());

			pst.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	// CONSULTA CONTA
	public static ArrayList<ContaModel> listaConta() {
		listaCont = new ArrayList<ContaModel>();
		try {
			String SQL_SELECT_QUERY = "SELECT * from conta";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			rs = pst.executeQuery();

			while (rs.next()) {
				contModel = new ContaModel();
				contModel.setIdConta(rs.getInt("id_conta"));
				contModel.setEmail(rs.getString("email"));
				contModel.setPerfil(rs.getString("perfil"));
				contModel.setUsuario(rs.getString("usuario"));
				contModel.setSenha(rs.getString("senha"));

				listaCont.add(contModel);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return listaCont;
	}
	
	// RECUPERAR CREDÊNCIAIS DO USUÁRIO
	public static boolean recuperarConta(ContaModel contac) {
		//O ID GERADO PELO SISTEMA E O USUÁRIO FUNCIONARÁ COM ID
		try {
			String SQL_UPDATE_QUERY = "UPDATE conta SET senha = ? WHERE usuario = ? and email = ?";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_UPDATE_QUERY);

			pst.setString(1, contac.getSenha());
			pst.setString(2, contac.getUsuario());
			pst.setString(3, contac.getEmail());

			pst.executeUpdate();
			pst.close();
			con.close();

			return true;

		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}
	
	public static void main(String []args) {
		ContaModel c = new ContaModel();
		c.setIdConta(2);
		c.setEmail("fafa@dalinacademy.com");
		c.setPerfil("Administrador");
		c.setUsuario("fatima.mate");
		c.setSenha("fafa,2023");
		System.out.println(ConnectionDAO.getConnection());
		/*System.out.println(ContaDAO.insertConta(c));
		System.out.println(ContaDAO.listaConta().get(1).getEmail());*/
		//System.out.println(ContaDAO.recuperarConta(c));
		System.out.println(ContaController.getUserId());
	}
}
