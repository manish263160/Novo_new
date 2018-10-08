package com.novoboot.wrapper.response;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

public class PaymentReceiveModel  extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2676862582018233261L;

	private String id;
	private String phone;
	private String email;
	@SerializedName("buyer_name")
	private String buyerName;
	private String amount;
	private String purpose;
	private String status;
	private String[] payments;
	@SerializedName("send_sms")
	private String sendSms;
	@SerializedName("send_email")
	private String sendEmail;
	@SerializedName("sms_status")
	private String smsStatus;
	@SerializedName("email_status")
	private String emailStatus;
	private String shorturl;
	private String longurl;
	@SerializedName("allow_repeated_payments")
	private boolean allowRepeatedPayments;
	@SerializedName("mark_fulfilled")
	private boolean markFulfilled;
	public String getId() {
		return id;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public String getAmount() {
		return amount;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getStatus() {
		return status;
	}
	public String[] getPayments() {
		return payments;
	}
	public String getSendSms() {
		return sendSms;
	}
	public String getSendEmail() {
		return sendEmail;
	}
	public String getSmsStatus() {
		return smsStatus;
	}
	public String getEmailStatus() {
		return emailStatus;
	}
	public String getShorturl() {
		return shorturl;
	}
	public String getLongurl() {
		return longurl;
	}
	public boolean isAllowRepeatedPayments() {
		return allowRepeatedPayments;
	}
	public boolean isMarkFulfilled() {
		return markFulfilled;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setPayments(String[] payments) {
		this.payments = payments;
	}
	public void setSendSms(String sendSms) {
		this.sendSms = sendSms;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}
	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	public void setAllowRepeatedPayments(boolean allowRepeatedPayments) {
		this.allowRepeatedPayments = allowRepeatedPayments;
	}
	public void setMarkFulfilled(boolean markFulfilled) {
		this.markFulfilled = markFulfilled;
	}
	@Override
	public String toString() {
		return "PaymentReceiveModel [id=" + id + ", phone=" + phone + ", email=" + email + ", buyerName=" + buyerName
				+ ", amount=" + amount + ", purpose=" + purpose + ", status=" + status + ", payments="
				+ Arrays.toString(payments) + ", sendSms=" + sendSms + ", sendEmail=" + sendEmail + ", smsStatus="
				+ smsStatus + ", emailStatus=" + emailStatus + ", shorturl=" + shorturl + ", longurl=" + longurl
				+ ", allowRepeatedPayments=" + allowRepeatedPayments + ", markFulfilled=" + markFulfilled + "]";
	}
}
