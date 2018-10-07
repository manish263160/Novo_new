package com.novoboot.wraper.model;

import java.io.Serializable;

public class WebHookModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2726696310867003142L;
	
	private long userId;
	private String payment_id;
	private String status;
	private String shorturl;
	private String longurl;
	private String purpose;
	private double amount;
	private String fees;
	private String currency;
	private String buyer;
	private String buyer_name;
	private String buyer_phone;
	private String payment_request_id;
	private String mac;
	
	/**
	 * @param payment_id
	 * @param status
	 * @param shorturl
	 * @param longurl
	 * @param purpose
	 * @param amount
	 * @param fees
	 * @param currency
	 * @param buyer
	 * @param buyer_name
	 * @param buyer_phone
	 * @param payment_request_id
	 * @param mac
	 */
	public WebHookModel(String payment_id, String status, String shorturl, String longurl, String purpose,
			double amount, String fees, String currency, String buyer, String buyer_name, String buyer_phone,
			String payment_request_id, String mac) {
		super();
		this.payment_id = payment_id;
		this.status = status;
		this.shorturl = shorturl;
		this.longurl = longurl;
		this.purpose = purpose;
		this.amount = amount;
		this.fees = fees;
		this.currency = currency;
		this.buyer = buyer;
		this.buyer_name = buyer_name;
		this.buyer_phone = buyer_phone;
		this.payment_request_id = payment_request_id;
		this.mac = mac;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public String getStatus() {
		return status;
	}
	public String getShorturl() {
		return shorturl;
	}
	public String getLongurl() {
		return longurl;
	}
	public String getPurpose() {
		return purpose;
	}
	public double getAmount() {
		return amount;
	}
	public String getFees() {
		return fees;
	}
	public String getCurrency() {
		return currency;
	}
	public String getBuyer() {
		return buyer;
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public String getBuyer_phone() {
		return buyer_phone;
	}
	public String getPayment_request_id() {
		return payment_request_id;
	}
	public String getMac() {
		return mac;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}
	public void setPayment_request_id(String payment_request_id) {
		this.payment_request_id = payment_request_id;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Override
	public String toString() {
		return "WebhookResponseModel [payment_id=" + payment_id + ", status=" + status + ", shorturl=" + shorturl
				+ ", longurl=" + longurl + ", purpose=" + purpose + ", amount=" + amount + ", fees=" + fees
				+ ", currency=" + currency + ", buyer=" + buyer + ", buyer_name=" + buyer_name + ", buyer_phone="
				+ buyer_phone + ", payment_request_id=" + payment_request_id + ", mac=" + mac + "]";
	}

}
