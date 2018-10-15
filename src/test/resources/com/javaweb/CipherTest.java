package com.javaweb;

import com.javaweb.security.Cipher;

public class CipherTest {
	
	
	public static void main(String[] args) {
		Cipher cipher = new Cipher("javaweb@gmail.com", "password");
		String hashedPassword = cipher.hashedPassword();
		System.out.println(hashedPassword);
	}
}
