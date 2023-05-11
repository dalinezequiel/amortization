package com.simor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.simor.model.DatabasePropertyModel;
import property.Application;

public class ConnectionDAO {
	private static Connection connection = null;
	private static DatabasePropertyModel databaseModel = null;

	public ConnectionDAO() {
		super();
	}

	public static Connection getConnection() {
		databaseModel = new Application().databaseProperties();
		try {
			Class.forName(databaseModel.getDriver());
			connection = DriverManager.getConnection(databaseModel.getUrl(), databaseModel.getUser(),
					databaseModel.getPass());
			return connection;
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
