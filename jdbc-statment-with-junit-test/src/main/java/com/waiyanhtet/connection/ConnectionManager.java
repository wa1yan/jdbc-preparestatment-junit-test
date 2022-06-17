package com.waiyanhtet.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionManager {

	String url = "jdbc:mysql://localhost:3306/jdbctest"; //jdbctest is database name
	String user = "****"; //replace user for database
	String password = "****"; //replace password database

	Connection getConnection() throws SQLException;

	static ConnectionManager getInstance() {
		return () -> DriverManager.getConnection(url, user, password);
	}
}
