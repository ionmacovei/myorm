package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	public static Connection getConnection() {

		try {

			 Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		System.out.println("Not found mysql JDBC Driver");

		e.printStackTrace();

		}

	   Connection connection = null;

		try {

			connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

		

		} catch (SQLException e) {

		System.out.println("Connection Failed! Check output console");

		e.printStackTrace();

		}

		return connection;

		}

}
