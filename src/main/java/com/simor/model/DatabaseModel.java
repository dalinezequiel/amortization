package com.simor.model;

public class DatabaseModel {
	private String server;
	private String database;
	private int port;
	private String user;
	private String pass;
	private String driver;
	private String url;

	public DatabaseModel() {
		super();
	}

	public DatabaseModel(String server, String database, int port, String user, String pass) {
		super();
		this.server = server;
		this.database = database;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
