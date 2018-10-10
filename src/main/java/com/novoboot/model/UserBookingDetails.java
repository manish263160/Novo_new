package com.novoboot.model;

import java.io.Serializable;

public class UserBookingDetails extends BaseDto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4054039435442034117L;
	
	public UserBookingDetails() {}
	private int id;
	private String paymentId;
	private String paymentRequestId;
	private String transactionId;
	private long userId;
	private String consumerName;
	private String consumerPhone;
	private String consumerEmail;
	private String description;
	private String successUrl;
	private String failUrl;
	
	private long serviceCatId;
	private long serviceMasterId;
	private String serviceCostIdList;
	private String serviceCatName;
	private String serviceName;
	private String comboPackages;
	private String extraPackages;
	private int status;
	private double totalAmount;
	private String couponApplied;
	private String userAddress;
	private int pinCode;
	private String city;
	private String bookingDate;
	private String bookingTime;	
	private String bookingStatus;
	
	/**
	 * This constructor for create a user booking detail by its field.
	 * @param transactionId , transaction id that we used on craete payment url
	 */
	public UserBookingDetails(String paymentRequestId ,String transactionId, long userId, String consumerName, String consumerPhone,
			String consumerEmail, String description, String successUrl, String failUrl, long serviceCatId,
			long serviceMasterId, String serviceCostIdList, String serviceCatName, String serviceName,
			String comboPackages, String extraPackages, int status, double totalAmount, String couponApplied,
			String userAddress, int pinCode, String city, String bookingDate, String bookingTime,
			String bookingStatus) {
		super();
		this.paymentRequestId = paymentRequestId;
		this.transactionId = transactionId;
		this.userId = userId;
		this.consumerName = consumerName;
		this.consumerPhone = consumerPhone;
		this.consumerEmail = consumerEmail;
		this.description = description;
		this.successUrl = successUrl;
		this.failUrl = failUrl;
		this.serviceCatId = serviceCatId;
		this.serviceMasterId = serviceMasterId;
		this.serviceCostIdList = serviceCostIdList;
		this.serviceCatName = serviceCatName;
		this.serviceName = serviceName;
		this.comboPackages = comboPackages;
		this.extraPackages = extraPackages;
		this.status = status;
		this.totalAmount = totalAmount;
		this.couponApplied = couponApplied;
		this.userAddress = userAddress;
		this.pinCode = pinCode;
		this.city = city;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.bookingStatus = bookingStatus;
	}
	public int getId() {
		return id;
	}
	public long getUserId() {
		return userId;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public String getConsumerPhone() {
		return consumerPhone;
	}
	public String getConsumerEmail() {
		return consumerEmail;
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
	public long getServiceCatId() {
		return serviceCatId;
	}
	public long getServiceMasterId() {
		return serviceMasterId;
	}
	public String getServiceCostIdList() {
		return serviceCostIdList;
	}
	public String getServiceCatName() {
		return serviceCatName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public String getComboPackages() {
		return comboPackages;
	}
	public String getExtraPackages() {
		return extraPackages;
	}
	public int getStatus() {
		return status;
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
	public String getBookingDate() {
		return bookingDate;
	}
	public String getBookingTime() {
		return bookingTime;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public void setConsumerPhone(String consumerPhone) {
		this.consumerPhone = consumerPhone;
	}
	public void setConsumerEmail(String consumerEmail) {
		this.consumerEmail = consumerEmail;
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
	public void setServiceCatId(long serviceCatId) {
		this.serviceCatId = serviceCatId;
	}
	public void setServiceMasterId(long serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}
	public void setServiceCostIdList(String serviceCostId) {
		this.serviceCostIdList = serviceCostId;
	}
	public void setServiceCatName(String serviceCatName) {
		this.serviceCatName = serviceCatName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setComboPackages(String comboPackages) {
		this.comboPackages = comboPackages;
	}
	public void setExtraPackages(String extraPackages) {
		this.extraPackages = extraPackages;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
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
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@Override
	public String toString() {
		return "UserBookingDetails [id=" + id + ", paymentId=" + paymentId + ", paymentRequestId=" + paymentRequestId
				+ ", transactionId=" + transactionId + ", userId=" + userId + ", consumerName=" + consumerName
				+ ", consumerPhone=" + consumerPhone + ", consumerEmail=" + consumerEmail + ", description="
				+ description + ", successUrl=" + successUrl + ", failUrl=" + failUrl + ", serviceCatId=" + serviceCatId
				+ ", serviceMasterId=" + serviceMasterId + ", serviceCostIdList=" + serviceCostIdList
				+ ", serviceCatName=" + serviceCatName + ", serviceName=" + serviceName + ", comboPackages="
				+ comboPackages + ", extraPackages=" + extraPackages + ", status=" + status + ", totalAmount="
				+ totalAmount + ", couponApplied=" + couponApplied + ", userAddress=" + userAddress + ", pinCode="
				+ pinCode + ", city=" + city + ", bookingDate=" + bookingDate + ", bookingTime=" + bookingTime
				+ ", bookingStatus=" + bookingStatus + "]";
	}
	
	
}
