package com.simor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.simor.model.ContaModel;

public class AccountDAO {
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	private static ArrayList<ContaModel> listaCont = null;
	private static ContaModel contModel = null;

	// CRIAR CREDÊNCIAIS DO USUÁRIO
	public static boolean insert(ContaModel model) {
		try {
			String SQL_INSERT_QUERY = "INSERT INTO account(id_account, email, usr, pass) values(?,?,?,?)";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_INSERT_QUERY);

			pst.setInt(1, model.getIdConta());
			pst.setString(2, model.getEmail());
			pst.setString(3, model.getUsuario());
			pst.setString(4, model.getSenha());

			pst.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	// CONSULTA CONTA
	public static ArrayList<ContaModel> list() {
		listaCont = new ArrayList<ContaModel>();
		try {
			String SQL_SELECT_QUERY = "SELECT * from account";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			rs = pst.executeQuery();

			while (rs.next()) {
				contModel = new ContaModel();
				contModel.setIdConta(rs.getInt("id_account"));
				contModel.setEmail(rs.getString("email"));
				contModel.setUsuario(rs.getString("usr"));
				contModel.setSenha(rs.getString("pass"));

				listaCont.add(contModel);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return listaCont;
	}

	// CONSULTA CONTA
	public static boolean login(ContaModel model) {
		try {
			String SQL_SELECT_QUERY = "SELECT * from account where (usr=? or email=?) and pass=?";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			pst.setString(1, model.getUsuario());
			pst.setString(2, model.getEmail());
			pst.setString(3, model.getSenha());
			rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	// VERIFICAR SE O USUARIO EXISTE ANTES DE ACTUALIZAR
	public static boolean userExist(ContaModel model) {
		try {
			String SQL_SELECT_QUERY = "SELECT * from account where usr=? and email=?";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			pst.setString(1, model.getUsuario());
			pst.setString(2, model.getEmail());
			rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	// RECUPERAR CREDÊNCIAIS DO USUÁRIO
	public static boolean recovery(ContaModel model) {
		// O ID GERADO PELO SISTEMA E O USUÁRIO FUNCIONARÁ COM ID
		try {
			if (userExist(model)) {
				String SQL_UPDATE_QUERY = "UPDATE account SET pass = ? WHERE usr = ? and email = ?";
				con = ConnectionDAO.getConnection();
				pst = con.prepareStatement(SQL_UPDATE_QUERY);

				pst.setString(1, model.getSenha());
				pst.setString(2, model.getUsuario());
				pst.setString(3, model.getEmail());

				pst.executeUpdate();
				pst.close();
				con.close();
				return true;
			}

		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	public static void main(String[] args) {
		ContaModel c = new ContaModel();
		c.setEmail("marcia.pedro@gmail.com");
		c.setUsuario("marcia.pedro");
		c.setSenha("marcia2023");
		System.out.println(AccountDAO.recovery(c));
	}
}
