package com.simor.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.simor.model.FeriadoModel;

public class HolidayDAO {
	private static Connection con = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	private static ArrayList<FeriadoModel> listaFeria = null;
	private static FeriadoModel feriaModel = null;

	// REGISTO NO CACHE
	public static boolean insert(FeriadoModel feria) {
		try {
			String SQL_INSERT_QUERY = "INSERT INTO holiday(id_holiday, holiday, description, holyear) values(?,?,?,?)";
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
	public static ArrayList<FeriadoModel> list() {
		listaFeria = new ArrayList<FeriadoModel>();
		try {
			String SQL_SELECT_QUERY = "SELECT * from holiday";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_SELECT_QUERY);
			rs = pst.executeQuery();

			while (rs.next()) {
				feriaModel = new FeriadoModel();
				feriaModel.setCodigo(rs.getInt("id_holiday"));
				feriaModel.setDataFeriado(rs.getDate("holiday"));
				feriaModel.setDescricao(rs.getString("description"));
				feriaModel.setAno(rs.getInt("holyear"));

				listaFeria.add(feriaModel);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return listaFeria;
	}
	
	// CONSULTA FERIADO BY DATE
		public static FeriadoModel holidayByDate(Date holiday) {
			try {
				String SQL_SELECT_QUERY = "SELECT * from holiday where holiday=?";
				con = ConnectionDAO.getConnection();
				pst = con.prepareStatement(SQL_SELECT_QUERY);
				pst.setDate(1, holiday);
				rs = pst.executeQuery();

				if (rs.next()) {
					feriaModel = new FeriadoModel();
					feriaModel.setCodigo(rs.getInt("id_holiday"));
					feriaModel.setDataFeriado(rs.getDate("holiday"));
					feriaModel.setDescricao(rs.getString("description"));
					feriaModel.setAno(rs.getInt("holyear"));
				}
			} catch (SQLException e) {
				System.out.println("Ocorreu um erro!\n" + e.getMessage());
			}
			return feriaModel;
		}

	public static boolean deleteById(int id) {
		try {
			String SQL_DELETE_QUERY = "DELETE FROM holiday WHERE id_holiday = ?";
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
	
	public static boolean deleteByDate(Date id) {
		try {
			String SQL_DELETE_QUERY = "DELETE FROM holiday WHERE holiday = ?";
			con = ConnectionDAO.getConnection();
			pst = con.prepareStatement(SQL_DELETE_QUERY);
			pst.setDate(1, id);
			pst.executeUpdate();
			pst.close();

			return true;
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro!\n" + e.getMessage());
		}
		return false;
	}
	
	public static void main(String [] args) {
		System.out.println(HolidayDAO.holidayByDate(Date.valueOf("2023-05-17")).getAno());
	}

}
