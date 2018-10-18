package com.javaweb.common.user;

public class UserRegistrationForm implements ApplicationForm {
	
	private String emailAddress;
	
	private String password;
	
	private String passwordConfirm;

	@Override
	public String emailAddress() {
		return emailAddress;
	}

	@Override
	public String password() {
		return password;
	}

	@Override
	public String passwordConfirm() {
		return passwordConfirm;
	}

}
