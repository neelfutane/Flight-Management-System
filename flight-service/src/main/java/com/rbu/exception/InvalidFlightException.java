package com.rbu.exception;

public class InvalidFlightException extends RuntimeException {
	
	public InvalidFlightException() {
		super();
	}
	public InvalidFlightException(String message) {
		super(message);
	}
}
