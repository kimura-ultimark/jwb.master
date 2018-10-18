package com.javaweb.common.user;

public interface UserReception {
	
	public IUser signIn(String emailAddress, String password);
	
	public IUser SignUp(ApplicationForm applicationForm) throws UserNotRegisteredException;
}