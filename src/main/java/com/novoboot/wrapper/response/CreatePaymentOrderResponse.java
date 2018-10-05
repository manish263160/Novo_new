package com.novoboot.wrapper.response;

import com.google.gson.annotations.SerializedName;

/**
 * The Class CreatePaymentOrderResponse.
 */
public class CreatePaymentOrderResponse extends Response {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2909422866001524099L;
	
	private String id;
	private String user;
	private String phone;
	private String email;
	
	@SerializedName("buyer_name")
	private String buyerName;
	private String amount;
	private String purpose;
	private String status;
	private String longurl;
	@SerializedName("resource_uri")
	private String resourceUri;
	
	public String getId() {
		return id;
	}
	public String getUser() {
		return user;
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
	public String getLongurl() {
		return longurl;
	}
	public String getResourceUri() {
		return resourceUri;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUser(String user) {
		this.user = user;
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
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
	 
}
