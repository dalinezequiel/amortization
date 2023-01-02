package com.simor.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.simor.model.DatabaseModel;

public class ConnectionDAO {
	private static Connection connection = null;
	private static File file = null;
	private static FileReader fileReader = null;
	private static BufferedReader bufferedReader = null;
	private static DatabaseModel databaseModel = null;

	private static String server;
	private static String database;
	private static int port;
	private static String user;
	private static String pass;
	private static String driver;
	private static String url;
	//private static String resultConnection;

	public ConnectionDAO() {
		super();
		databaseModel = new DatabaseModel();
		//resultConnection = "org.postgresql.jdbc.PgConnection";
	}
	
	//OBJECTO DATABASEMODEL
	public static DatabaseModel getDatabaseModel() {
		readFromFile();
		getConnection();
		databaseModel = new DatabaseModel();
		if (String.valueOf(getConnection()).contains("org.postgresql.jdbc.PgConnection")) {
			databaseModel.setServer(server);
			databaseModel.setDatabase(database);
			databaseModel.setPort(port);
			databaseModel.setUser(user);
			databaseModel.setPass(pass);
			
			return databaseModel;
		}
		return null;
	}

	// LEITURA DOS DADOS DE CONFIGURAÇÃO DO BANCO DO FICHEIRO
	public static void readFromFile() {
		file = new File("C:\\Users\\Public\\simor_set.txt");//C:\\Users\\Public\\simor_set.txt
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			server = bufferedReader.readLine();
			database = bufferedReader.readLine();
			port = Integer.parseInt(bufferedReader.readLine());
			user = bufferedReader.readLine();
			pass = bufferedReader.readLine();

			bufferedReader.close();
			fileReader.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	// RETORNA A CONEXÃO COM O BANCO DE DADOS
	public static Connection getConnection() { // static
		readFromFile();
		driver = "org.postgresql.Driver";
		url = "jdbc:postgresql://" + server + ":" + port + "/" + database;

		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pass);
			return connection;
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public static void main(String[] args) {
		//System.out.println(ConnectionDAO.getConnection());
		System.out.println(ConnectionDAO.getDatabaseModel().getDatabase());
	}
}
