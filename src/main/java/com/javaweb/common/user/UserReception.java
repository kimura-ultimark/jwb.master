package com.javaweb.common.user;

public interface UserReception {
	
	public IUser signIn(String emailAddress, String password);
	
	public void SignUp(ApplicationForm applicationForm) throws UserNotRegisteredException;
}