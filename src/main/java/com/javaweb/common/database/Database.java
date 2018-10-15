package com.javaweb.common.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Database {
	
	private String jndiResourceName;
	
	public Database(String jndiResourceName) {
		this.jndiResourceName = jndiResourceName;
	}
	
	public Connection connect() throws SQLException {
		Connection conn = null;
		try {
			// JNDIよりConnection取得
			DataSource dataSource = InitialContext.doLookup("java:comp/env/" + jndiResourceName);
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException e) {
			// DriverManageよりConnection取得
			String url = "jdbc:mysql://192.168.1.15:3306/awgdb?characterEncoding=UTF-8&serverTimezone=JST&autoReconnect=true&useSSL=false";
			String user = "jwbuser";
			String password = "EqI`Iay6";
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		}
		return conn;
	}
}