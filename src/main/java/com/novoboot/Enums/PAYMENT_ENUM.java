package com.novoboot.Enums;

public enum PAYMENT_ENUM {

	PAYMENT_REQUESTS("payment-requests") , ALLOW_REPEATED_PAYMENTS("allow_repeated_payments"),
	AMOUNT("amount") , BUYER_NAME("buyer_name") , PURPOSE("purpose") , PHONE("phone") , SEND_EMAIL("send_email"),
	SEND_SMS("send_sms") , EMAIL("email"),
	X_API_KEY("X-Api-Key") , X_AUTH_TOKEN("X-Auth-Token");
	
	private String value;
	
	private PAYMENT_ENUM(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value + ": " /*+ value*/;
	}
}
