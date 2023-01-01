package com.simor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.simor.model.*;

public class CacheDAO {

	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	private static ArrayList<CacheModel> listaChe = null;
	private static CacheModel cheModel = null;

	// REGISTO NO CACHE
	public static boolean insertIntoCache(CacheModel cache) {
		try {
			String SQL_INSERT_QUERY = "INSERT INTO tb_cache(id_cache, email, prestacao, juro, amortizacao, saldo_devedor) values(?,?,?,?,?,?)";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_INSERT_QUERY);

			/*pst.setInt(1, cache.getIdCache());
			pst.setString(2, cache.getEmail());*/
			pst.setInt(1, 2023);
			pst.setString(2, "email");
			pst.setDouble(3, cache.getPrestacao());
			pst.setDouble(4, cache.getJuro());
			pst.setDouble(5, cache.getAmortizacao());
			pst.setDouble(6, cache.getSaldoDevedor());

			pst.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}

	// CONSULTA CACHE
	public static ArrayList<CacheModel> listaCache() {
		listaChe = new ArrayList<CacheModel>();
		try {
			String SQL_SELECT_QUERY = "SELECT * from tb_cache";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			rs = pst.executeQuery();

			while (rs.next()) {
				cheModel = new CacheModel();
				cheModel.setIdCache(rs.getInt("id_cache"));
				cheModel.setEmail(rs.getString("email"));
				cheModel.setPrestacao(Double.parseDouble(rs.getString("prestacao")));
				cheModel.setJuro(Double.parseDouble(rs.getString("juro")));
				cheModel.setAmortizacao(Double.parseDouble(rs.getString("amortizacao")));
				cheModel.setSaldoDevedor(Double.parseDouble(rs.getString("saldo_devedor")));

				listaChe.add(cheModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaChe;
	}
	public static void main(String []args) {
		System.out.println(ConnectionDAO.getConnection());
	}
}
