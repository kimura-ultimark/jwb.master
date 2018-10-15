package com.javaweb;

import java.sql.Connection;
import java.sql.SQLException;

import com.javaweb.common.database.Database;

public class ConnectionTest {
	
	public static void main(String[] args) {
		Database database = new Database("jdbc/awg");
		try {
			Connection conn = database.connect();
			System.out.println(conn.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}