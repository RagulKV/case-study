package com.cognizant.truyum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.util.Properties;

public class ConnectionHandler {

	private static Connection con = null;
	private static Properties props = new Properties();
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		// load the Driver Class
		FileInputStream fis = null;
		fis = new FileInputStream("src/main/resources/connection.properties");
		props.load(fis);
		Class.forName(props.getProperty("driver"));
		//Class.forName("com.mysql.jdbc.Driver");  
		// create the connection now
          con = DriverManager.getConnection(props.getProperty("connection-url"),props.getProperty("user"),props.getProperty("password"));
		//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lch_marketplace", "vengatesh" , "vengrohini99");
		return con;
		
		
	}
	
//	public static void main(String[] args) {
//		try {
//			System.out.println(ConnectionHandler.getConnection());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
