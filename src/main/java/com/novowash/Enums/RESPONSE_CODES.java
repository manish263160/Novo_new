package com.novowash.Enums;

/**
 * 
 * @author mukeshks
 *
 */
public enum RESPONSE_CODES {
	
	FAIL (1000, "FAIL" , "Something went wrong."),
	INCORRECT_JSON_FORMAT (1001, "The json request seems to be malformed.", "Something went wrong.Please try again!"),
	EMPTY_REQUEST_BODY(1002,"Request Body is empty", "Please try again!"),
	INVALID_USER_AUTH_KEY(1003,"",""),
	DAO_EXCEPTION(1004,"FAIL","JDBC Exception"),
	SUCCESS(200,"SUCCESS","Success"),
	USER_EXIST(1005,"USER_EXIST" , "User already exist");
	//EMPTY_REQUEST_BODY
	
	private final int code;
	private final String desc;
	private final String message;

	private RESPONSE_CODES(int code, String description , String message) {
		this.code = code;
		this.desc = description;
		this.message = message;
	}

	public String getDescription() {
		return desc;
	}

	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return code + ": " + desc;
	}
}
