package fr.fms.dao;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class RequestDatabase {
	public static ArrayList<Article> readDatabase(String request) {
		ArrayList<Article> articles = new ArrayList<Article>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String 	url = "jdbc:mariadb://localhost:3306/shop",
				login = "root",
				password = "fms2024";

		try (Connection connect = DriverManager.getConnection(url,login,password)){
			String strSql = request;
			try(Statement statement = connect.createStatement()){
				try (ResultSet  resultSet = statement.executeQuery(strSql)){
					while(resultSet.next()) {
						String rsDescription = resultSet.getString(1);
						String rsBrand = resultSet.getString(2);
						double rsPrice = resultSet.getDouble(3);
						articles.add(new Article(rsDescription, rsBrand, rsPrice));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

	public static void writeDataBase(String request) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String 	url = "jdbc:mariadb://localhost:3306/shop",
				login = "root",
				password = "fms2024";

		try (Connection connect = DriverManager.getConnection(url,login,password)){
			String strSql = request;
			try(Statement statement = connect.createStatement()){
				try (ResultSet  resultSet = statement.executeQuery(strSql)){
					System.out.println("Requête transféré");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
