package com.simor.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.simor.model.DatabasePropertyModel;

public class ConnectionDAO {
	private static Connection connection = null;
	private static DatabasePropertyModel databaseModel = null;
	private static FileOutputStream fos = null;
	private static FileInputStream fis = null;
	private static Properties props = null;

	public ConnectionDAO() {
		super();
	}
	
	/* WRITE PROPERTIES */
	public static boolean writeProperties(DatabasePropertyModel model) {
		File file =new File("resources/application.properties");
		try {
			file.setReadable(true, false);
			fos = new FileOutputStream(file);
			props = new Properties();
			props.setProperty("simor.datasource.server", model.getServer());
			props.setProperty("simor.datasource.databasename", model.getDatabase());
			props.setProperty("simor.datasource.port", String.valueOf(model.getPort()));
			props.setProperty("simor.datasource.username", model.getUser());
			props.setProperty("simor.datasource.password", model.getPass());
			props.store(fos, "Database configuration");
			fos.close();
			System.out.println("File created successfully!");
			return true;

		} catch (Exception ex) {
			System.out.println("Properties problems!\n" + ex.getMessage());
		}
		return false;
	}

	/* READ PROPERTIES */
	public static DatabasePropertyModel readProperties() {
		//File file =new File("application.properties");
		databaseModel = new DatabasePropertyModel();;
		props = new Properties();
		try {
			//fis=new FileInputStream(file);
			//props.load(fis);
			databaseModel.setDriver("org.postgresql.Driver");
			
//			databaseModel.setServer(props.getProperty("simor.datasource.server"));
//			databaseModel.setDatabase(props.getProperty("simor.datasource.databasename"));
//			databaseModel.setPort(Integer.parseInt(props.getProperty("simor.datasource.port")));
//			databaseModel.setUser(props.getProperty("simor.datasource.username"));
//			databaseModel.setPass(props.getProperty("simor.datasource.password"));
			databaseModel.setServer("localhost");
			databaseModel.setDatabase("simor");
			databaseModel.setPort(5432);
			databaseModel.setUser("postgres");
			databaseModel.setPass("hund,70");
			
			databaseModel.setUrl("jdbc:postgresql://" + databaseModel.getServer() + ":" + databaseModel.getPort() + "/" + databaseModel.getDatabase());
			fis.close();
			System.out.println(databaseModel.getServer());
			System.out.println(databaseModel.getDatabase());
			System.out.println(databaseModel.getPort());
			System.out.println(databaseModel.getUser());
			System.out.println(databaseModel.getPass());
			System.out.println("Lido!!");
		}catch(Exception ex) {
			System.out.println("Ocorreu um erro ao tentar ler as propriedades do banco!\n"+ex.getMessage());
		}
		return databaseModel;
	}


	public static Connection getConnection() {
		databaseModel=readProperties();
		try {
			Class.forName(databaseModel.getDriver());
			connection = DriverManager.getConnection(databaseModel.getUrl(), databaseModel.getUser(), databaseModel.getPass());
			return connection;
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public static void main(String[] args) {
//		DatabasePropertyModel databaseModel=new DatabasePropertyModel();
//		databaseModel.setServer("localhost");
//		databaseModel.setDatabase("simor");
//		databaseModel.setPort(5432);
//		databaseModel.setUser("postgres");
//		databaseModel.setPass("root");
//		ConnectionDAO.writeProperties(databaseModel);
		ConnectionDAO.readProperties();
		//System.out.println(ConnectionDAO.getConnection());
//		System.out.println("Ola");
//		ConnectionDAO.readFromFileWW();
	}
}
