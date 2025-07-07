package com.jeffersonssousa.investHub.services.exceptions;

public class ControllerNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ControllerNotFoundException(Object obj) {
		super("Resource not found. Id " + obj);
	}
	

}
