package com.novoboot.model;

import java.io.Serializable;

public class UserPackageTakenDates implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3064827357112913953L;
	
	private long id;
	private long userPackageBookingId;
	private long userId;
	private String bookingDate;
	private String bookingTime;
	private int numberOfUsed;
	public long getId() {
		return id;
	}
	public long getUserPackageBookingId() {
		return userPackageBookingId;
	}
	public long getUserId() {
		return userId;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public String getBookingTime() {
		return bookingTime;
	}
	public int getNumberOfUsed() {
		return numberOfUsed;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUserPackageBookingId(long userPackageBookingId) {
		this.userPackageBookingId = userPackageBookingId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public void setNumberOfUsed(int numberOfUsed) {
		this.numberOfUsed = numberOfUsed;
	}
	@Override
	public String toString() {
		return "UserPackageTakenDates [id=" + id + ", userPackageBookingId=" + userPackageBookingId + ", userId="
				+ userId + ", bookingDate=" + bookingDate + ", bookingTime=" + bookingTime + ", numberOfUsed="
				+ numberOfUsed + "]";
	}

}
