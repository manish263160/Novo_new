package com.novoboot.Enums;

public enum OTP_ENUMS {


	OTP ("otp"),EMAIL("email"),AUTH_KEY("authkey"),MESSAGE("message"),
	SEBDER("NOVOWASH"),MOBILE("mobile"),TEMPLATE("template"),OTP_LENGTH("6"),
	OTP_EXPIRY("5"),RETRYTYPE_TEXT("text"),RETRYTYPE_voice("voice"), /*default type is voice (voice/text)*/
	CONTENT_TYPE("Content-Type") , // use this: application/x-www-form-urlencoded 
	OTP_MESSAGE("your login otp is ##OTP##"),
	SENDER("NOVOWASH"),
	OTP_SENT_SUCCESSFUL("OTP_SENT_SUCCESSFUL"),
	OTP_SEND_FAIL("OTP_SEND_FAIL"),
	OTP_VLID_DURATION("Otp is valid only for 2 minuts"),
	//these are the otp verification message
	NUMBER_VERIFIED_SUCCESSFULLY("number_verified_successfully"),
	INVALID_OTP("invalid_otp"),
	MOBILE_NOT_FOUND("mobile_not_found"),
	ALREADY_VERIFIED("already_verified"),
	
	;
	
	
	private final String key;
//	private final String value;

	private OTP_ENUMS(String key/*, String value*/) {
		this.key = key;
//		this.value = value;
	}
	
	public String getKey() {
		return key;
	}

/*	public String getValue() {
		return value;
	}
*/	
	@Override
	public String toString() {
		return key + ": " /*+ value*/;
	}
	
}
