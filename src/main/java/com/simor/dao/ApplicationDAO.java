package com.simor.dao;

import com.simor.model.DatabasePropertyModel;

public class ApplicationDAO {
	protected String server, database, user, pass;
	protected int port;
	protected DatabasePropertyModel databaseModel = null;

	public ApplicationDAO(String server, String database, int port, String user, String pass) {
		super();
		this.server = server;
		this.database = database;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}

	protected DatabasePropertyModel databaseProperties() {
		databaseModel = new DatabasePropertyModel();
		databaseModel.setDriver("org.postgresql.Driver");
		databaseModel.setServer(this.server);
		databaseModel.setDatabase(this.database);
		databaseModel.setPort(this.port);
		databaseModel.setUser(this.user);
		databaseModel.setPass(this.pass);
		databaseModel.setUrl("jdbc:postgresql://" + databaseModel.getServer() + ":" + databaseModel.getPort() + "/"
				+ databaseModel.getDatabase());
		return databaseModel;
	}
}
