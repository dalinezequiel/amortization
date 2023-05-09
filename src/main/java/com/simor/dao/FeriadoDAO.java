package com.simor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.simor.model.FeriadoModel;

public class FeriadoDAO {
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	private static ArrayList<FeriadoModel> listaFeria = null;
	private static FeriadoModel feriaModel = null;

	// REGISTO NO CACHE
	public static boolean insertIntoFeriado(FeriadoModel feria) {
		try {
			String SQL_INSERT_QUERY = "INSERT INTO feriado(id_feriado, data_feriado, descricao, ano) values(?,?,?,?)";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_INSERT_QUERY);

			pst.setInt(1, feria.getCodigo());
			pst.setDate(2, feria.getDataFeriado());
			pst.setString(3, feria.getDescricao());
			pst.setInt(4, feria.getAno());

			pst.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	// CONSULTA FERIADO
	public static ArrayList<FeriadoModel> listaFeriado() {
		listaFeria = new ArrayList<FeriadoModel>();
		try {
			String SQL_SELECT_QUERY = "SELECT * from feriado";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			rs = pst.executeQuery();

			while (rs.next()) {
				feriaModel = new FeriadoModel();
				feriaModel.setCodigo(rs.getInt("id_feriado"));
				feriaModel.setDataFeriado(rs.getDate("data_feriado"));
				feriaModel.setDescricao(rs.getString("descricao"));
				feriaModel.setAno(rs.getInt("ano"));

				listaFeria.add(feriaModel);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return listaFeria;
	}
	
	public static boolean deleteFeriadoId(int id) {
		try {
			String SQL_DELETE_QUERY = "DELETE FROM feriado WHERE id_feriado = ?";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_DELETE_QUERY);
			pst.setInt(1, id);
			pst.executeUpdate();
			pst.close();

			return true;
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

}
