package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import fr.fms.entities.Article;

public class BddConnection {

	public static Connection getConnexion() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Shop", "root", "fms2024");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		/*
		Properties connectionProps = new Properties();
	    connectionProps.put("user", ReadConfigFile.getLogin());
	    connectionProps.put("password", ReadConfigFile.getPassword());
	    */
	    
	    //conn = DriverManager.getConnection(ReadConfigFile.getUrl());
	    System.out.println("Connected to database");
	    return conn;
	}
}
