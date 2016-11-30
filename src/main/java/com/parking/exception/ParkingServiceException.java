package com.parking.exception;

public class ParkingServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	private Throwable throwable;
	private Integer errorCode;
	
	public ParkingServiceException() {
		super();
	}
	public ParkingServiceException(Integer errorCode) {
		super();
		this.errorCode = errorCode;
	}
	public ParkingServiceException(String message) {
		super();
		this.message = message;
	}
	public ParkingServiceException(String message, Integer errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
