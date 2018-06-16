package com.PAYDAY;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public Connection ConnectReturn(){
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			@SuppressWarnings("unused")
			String dbDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";			//Á´½ÓÊý¾Ý¿â
			String dbUrl="jdbc:sqlserver://localhost:1433;integratedSecurity=false;DatabaseName=mydb";
			conn = DriverManager.getConnection(dbUrl,"sa","123456");
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			}
		return conn;
	}
}