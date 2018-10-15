package com.javaweb.common.user;

public interface UserReception {
	
	public void signUp(IUser newUser);
	
	public IUser signIn(String emailAddress, String password);
}