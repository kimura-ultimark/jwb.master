package com.javaweb.common.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaweb.common.database.Database;
import com.javaweb.security.Cipher;

public class UserManager implements UserReception {
	
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

	@Override
	public void SignUp(ApplicationForm applicationForm) 
			throws UserNotRegisteredException {
		// パスワードと確認用パスワードの比較
		if (!applicationForm.password().equals(applicationForm.passwordConfirm())) {
			throw new UserNotRegisteredException("パスワードが一致しません。");
		}
		// メールアドレスの存在チェック
		Database database = new Database("jdbc/awg");
		Connection conn = null;
		try {
			conn = database.connect();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE email = ? ");
			stmt.setString(1, applicationForm.emailAddress());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				throw new UserNotRegisteredException("メールアドレスは既に登録済です。");
			}
			rs.close();
			stmt.close();
			// パスワードのハッシュ化
			Cipher cipher = new Cipher(applicationForm.emailAddress(), applicationForm.password());
			String hashedPassword = cipher.hashedPassword();
			// ユーザーテーブルに登録
			PreparedStatement insStmt = conn.prepareStatement("INSERT INTO user (email, password) values (?, ?) ");
			insStmt.setString(1, applicationForm.emailAddress());
			insStmt.setString(2, hashedPassword);
			insStmt.executeUpdate();
			insStmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			throw new UserNotRegisteredException(e);
		}
	}
}