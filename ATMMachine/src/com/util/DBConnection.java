package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Global connection Class
public class DBConnection {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
//		Create connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/disha", "root", "root1427");
		System.out.println("connected");

		return con;
	}
}
