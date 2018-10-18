package com.javaweb.common.user;

import java.io.Serializable;

public interface ApplicationForm extends Serializable {
	
	public String emailAddress();
	
	public String password();
	
	public String passwordConfirm();
}
