package com.mobiquityinc.exception;

public class APIException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6309650033195055636L;
	
	private final ErrorCode code;
	
	public APIException(String message, Throwable cause, ErrorCode code) {
		super(message, cause);
		this.code = code;
	}	
	
	
	public APIException(String message,ErrorCode code) {
		super(message);
		this.code = code;
	}	
	
	
}
