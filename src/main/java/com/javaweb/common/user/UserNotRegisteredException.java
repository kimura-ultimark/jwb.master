package com.javaweb.common.user;

public class UserNotRegisteredException extends Exception {

	public UserNotRegisteredException(String string) {
		super(string);
	}

	public UserNotRegisteredException(Throwable e) {
		super(e);
	}
}