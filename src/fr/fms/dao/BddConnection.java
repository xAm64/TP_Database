package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import fr.fms.entities.Article;

public class BddConnection {

	public Connection getConnexion() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
	    connectionProps.put("user", ReadConfigFile.getLogin());
	    connectionProps.put("password", ReadConfigFile.getPassword());
	    
	    conn = DriverManager.getConnection(ReadConfigFile.getUrl());
	    System.out.println("Connected to database");
	    return conn;
	}
}
