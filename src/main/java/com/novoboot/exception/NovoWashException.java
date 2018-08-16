package com.novoboot.exception;

public class NovoWashException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7665456465175197769L;
	
	private String status;
	private int statusCode; 
//	private String description;
	private String message;

	/**
	 * This is the constructor for NovowashException class 
	 * @param status
	 * @param statusCode
	 * @param description
	 * @param message
	 */
	public NovoWashException(String status,int statusCode ,  String message) {
		this.status= status;
		this.statusCode = statusCode;
		this.message = message;
		
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
