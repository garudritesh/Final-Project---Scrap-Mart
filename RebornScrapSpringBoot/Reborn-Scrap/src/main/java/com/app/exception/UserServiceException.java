package com.app.exception;

public class UserServiceException extends RuntimeException{
	
	//private String message;

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message) {
		super(message);
		//this.message = message;
	}
	

}
