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
	
	private String buyerName;
	private String buyerPhone;
	private String currency;
	private String fees;
	private String longurl;
	private String mac;
	private String paymentId;
	private String paymentRequestId;
	private String purpose;
	private String shorturl;
	private String status;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerPhone() {
		return buyerPhone;
	}
	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getLongurl() {
		return longurl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentRequestId() {
		return paymentRequestId;
	}
	public void setPaymentRequestId(String paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getShorturl() {
		return shorturl;
	}
	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "WebHookModel [amount=" +this.amount + ", buyer=" +this.buyer + ", buyerName=" +this.buyerName + ", buyerPhone="
				+this.buyerPhone + ", currency=" +this.currency + ", fees=" +this.fees + ", longurl=" +this.longurl + ", mac=" +this.mac
				+ ", paymentId=" +this.paymentId + ", paymentRequestId=" +this.paymentRequestId + ", purpose=" +this.purpose
				+ ", shorturl=" +this.shorturl + ", status=" +this.status + "]";
	}
}
