package com.javaweb.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cipher {
	
	private String userId;
	
	private String password;
	
	private static final int STRETCH_COUNT = 2048;
	
	public Cipher(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	
	public String hashedPassword() {
		String salt = encode(userId);
		String hash = "";
		for (int i = 0; i < STRETCH_COUNT; i++) {
			hash = encode(hash + salt + password);
		}
		return hash;
	}
	
	private String encode(String srcText) {
		StringBuffer buffer = new StringBuffer();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(srcText.getBytes());
			byte[] byteArray = md.digest();
			for (byte digest : byteArray) {
				buffer.append(String.format("%02x", digest));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}