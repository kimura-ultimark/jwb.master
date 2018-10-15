package com.javaweb.common;

public enum Gender {
	
	MALE("Male"),FEMALE("Female");
	
	private String stringRepresentation;
	
	private Gender(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
	
	@Override
	public String toString() {
		return stringRepresentation;
	}
}