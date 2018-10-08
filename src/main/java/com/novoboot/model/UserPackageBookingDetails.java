package com.novoboot.model;

import java.io.Serializable;


public class UserPackageBookingDetails extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1272392739485204L;
	private int id;
	private String paymentRequestId;
	private String paymentId;
	private String transactionId;
	private long userId;
	private String consumerEmail;
	private String consumerName;
	private String consumerPhone;
	private String description;
	private String successUrl;
	private String failUrl;
	
	private long packageCatId;
	private long packageMasterId;
	private String packageCostIdList;
	private String packageCatName;
	private String packageName;
	private String comboPackages;
	private String extraPackages;
	private double totalAmount;
	private String couponApplied;
	private String userAddress;
	private int pinCode;
	private String city;
	private String lastBookingDate;
	private String lastBookingTime;	
	private String bookingStatus;
	private String expiredDate;
	
	
	
	public UserPackageBookingDetails(String paymentRequestId,  String transactionId, long userId,
			String consumerEmail, String consumerName, String consumerPhone, String description, String successUrl,
			String failUrl, long packageCatId, long packageMasterId, String packageCostIdList, String packageCatName,
			String packageName, String comboPackages, String extraPackages, double totalAmount, String couponApplied,
			String userAddress, int pinCode, String city, String lastBookingDate, String lastBookingTime,
			String bookingStatus , String expiredDate) {
		super();
		this.paymentRequestId = paymentRequestId;
		this.transactionId = transactionId;
		this.userId = userId;
		this.consumerEmail = consumerEmail;
		this.consumerName = consumerName;
		this.consumerPhone = consumerPhone;
		this.description = description;
		this.successUrl = successUrl;
		this.failUrl = failUrl;
		this.packageCatId = packageCatId;
		this.packageMasterId = packageMasterId;
		this.packageCostIdList = packageCostIdList;
		this.packageCatName = packageCatName;
		this.packageName = packageName;
		this.comboPackages = comboPackages;
		this.extraPackages = extraPackages;
		this.totalAmount = totalAmount;
		this.couponApplied = couponApplied;
		this.userAddress = userAddress;
		this.pinCode = pinCode;
		this.city = city;
		this.lastBookingDate = lastBookingDate;
		this.lastBookingTime = lastBookingTime;
		this.bookingStatus = bookingStatus;
		this.expiredDate = expiredDate;
	}
	
	public int getId() {
		return id;
	}
	public String getPaymentRequestId() {
		return paymentRequestId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public long getUserId() {
		return userId;
	}
	public String getConsumerEmail() {
		return consumerEmail;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public String getConsumerPhone() {
		return consumerPhone;
	}
	public String getDescription() {
		return description;
	}
	public String getSuccessUrl() {
		return successUrl;
	}
	public String getFailUrl() {
		return failUrl;
	}
	public long getPackageCatId() {
		return packageCatId;
	}
	public long getPackageMasterId() {
		return packageMasterId;
	}
	public String getPackageCostIdList() {
		return packageCostIdList;
	}
	public String getPackageCatName() {
		return packageCatName;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getComboPackages() {
		return comboPackages;
	}
	public String getExtraPackages() {
		return extraPackages;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public String getCouponApplied() {
		return couponApplied;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public int getPinCode() {
		return pinCode;
	}
	public String getCity() {
		return city;
	}
	public String getLastBookingDate() {
		return lastBookingDate;
	}
	public String getLastBookingTime() {
		return lastBookingTime;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPaymentRequestId(String paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setConsumerEmail(String consumerEmail) {
		this.consumerEmail = consumerEmail;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public void setConsumerPhone(String consumerPhone) {
		this.consumerPhone = consumerPhone;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
	public void setFailUrl(String failUrl) {
		this.failUrl = failUrl;
	}
	public void setPackageCatId(long packageCatId) {
		this.packageCatId = packageCatId;
	}
	public void setPackageMasterId(long packageMasterId) {
		this.packageMasterId = packageMasterId;
	}
	public void setPackageCostIdList(String packageCostIdList) {
		this.packageCostIdList = packageCostIdList;
	}
	public void setPackageCatName(String packageCatName) {
		this.packageCatName = packageCatName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setComboPackages(String comboPackages) {
		this.comboPackages = comboPackages;
	}
	public void setExtraPackages(String extraPackages) {
		this.extraPackages = extraPackages;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setCouponApplied(String couponApplied) {
		this.couponApplied = couponApplied;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setLastBookingDate(String lastBookingDate) {
		this.lastBookingDate = lastBookingDate;
	}
	public void setLastBookingTime(String lastBookingTime) {
		this.lastBookingTime = lastBookingTime;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	@Override
	public String toString() {
		return "UserPackageBookingDetails [id=" + id + ", paymentRequestId=" + paymentRequestId + ", paymentId="
				+ paymentId + ", transactionId=" + transactionId + ", userId=" + userId + ", consumerEmail="
				+ consumerEmail + ", consumerName=" + consumerName + ", consumerPhone=" + consumerPhone
				+ ", description=" + description + ", successUrl=" + successUrl + ", failUrl=" + failUrl
				+ ", packageCatId=" + packageCatId + ", packageMasterId=" + packageMasterId + ", packageCostIdList="
				+ packageCostIdList + ", packageCatName=" + packageCatName + ", packageName=" + packageName
				+ ", comboPackages=" + comboPackages + ", extraPackages=" + extraPackages + ", totalAmount="
				+ totalAmount + ", couponApplied=" + couponApplied + ", userAddress=" + userAddress + ", pinCode="
				+ pinCode + ", city=" + city + ", lastBookingDate=" + lastBookingDate + ", lastBookingTime="
				+ lastBookingTime + ", bookingStatus=" + bookingStatus + "]";
	}
	
	
}
