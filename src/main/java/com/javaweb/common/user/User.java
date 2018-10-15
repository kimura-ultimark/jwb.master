package com.javaweb.common.user;

public class User implements IUser {
	
	private boolean registerdUser;
	
	private boolean authenticated;
	
	public User(boolean registeredUser, boolean authenticated) {
		this.registerdUser = registeredUser;
		this.authenticated = authenticated;
	}
	
	@Override
	public boolean isRegisteredUser() {
		return registerdUser;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}
}