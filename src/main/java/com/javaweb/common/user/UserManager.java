package com.javaweb.common.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.common.database.Database;
import com.javaweb.security.Cipher;

public class UserManager implements UserReception {

	@Override
	public void signUp(IUser newUser) {
		
	}

	@Override
	public IUser signIn(String emailAddress, String password) {
		// パスワードのハッシュ化
		Cipher cipher = new Cipher(emailAddress, password);
		String hashedPassword = cipher.hashedPassword();
		// データベース問い合わせ
		Database database = new Database("jdbc/awg");
		// ユーザー
		IUser user = null;
		try {
			Connection conn = database.connect();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ? ");
			stmt.setString(1, emailAddress);
			stmt.setString(2, hashedPassword);
			ResultSet rs = stmt.executeQuery();
			boolean authenticated = rs.next();
			user = new User(authenticated, authenticated);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}