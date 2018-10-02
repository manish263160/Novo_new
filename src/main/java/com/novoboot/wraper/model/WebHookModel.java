package com.novoboot.wraper.model;

import java.io.Serializable;

public class WebHookModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -567379553992994275L;
	private long userId;
	private String amount;
	private String buyer;
	private String transactionId;
	private String buyer_name;
	private String buyer_phone;
	private String currency;
	private String fees;
	private String longurl;
	private String mac;
	private String payment_id;
	private String payment_request_id;
	private String purpose;
	private String shorturl;
	private String status;
	@Override
	public String toString() {
		return "WebHookModel [userId=" + userId + ", amount=" + amount + ", buyer=" + buyer + ", buyer_name="
				+ buyer_name + ", buyer_phone=" + buyer_phone + ", currency=" + currency + ", fees=" + fees
				+ ", longurl=" + longurl + ", mac=" + mac + ", payment_id=" + payment_id + ", payment_request_id="
				+ payment_request_id + ", purpose=" + purpose + ", shorturl=" + shorturl + ", status=" + status + "]";
	}
	public long getUserId() {
		return userId;
	}
	public String getAmount() {
		return amount;
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
	public String getCurrency() {
		return currency;
	}
	public String getFees() {
		return fees;
	}
	public String getLongurl() {
		return longurl;
	}
	public String getMac() {
		return mac;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public String getPayment_request_id() {
		return payment_request_id;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getShorturl() {
		return shorturl;
	}
	public String getStatus() {
		return status;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setAmount(String amount) {
		this.amount = amount;
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
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public void setPayment_request_id(String payment_request_id) {
		this.payment_request_id = payment_request_id;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
