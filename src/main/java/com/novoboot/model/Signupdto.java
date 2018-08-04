package com.novoboot.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class Signupdto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5884247800780900960L;

	@Pattern(regexp="(^$|[0-9]{10})" , message ="not matching with phone number")
	@NotBlank(message = "Task mobile must not be blank!")
	private String mobileNo;
	
	private long OTP;
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public long getOTP() {
		return OTP;
	}
	public void setOTP(long oTP) {
		OTP = oTP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
