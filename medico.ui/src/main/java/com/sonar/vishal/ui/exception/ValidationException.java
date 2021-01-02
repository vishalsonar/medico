package com.sonar.vishal.ui.exception;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -7717070099741930501L;

	public ValidationException() {
		super();
	}
	
	public ValidationException(String message) {
		super(message);
	}
}
