package com.novoboot.model;

import java.io.Serializable;

public class PackageCost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5365691327618543121L;
	private long id;
	private long packagesMasterId;
	private String duration;
	private String userInputs;
	private double unitPrice;
	private int isExtras;
	private String windowType;
	private String windowText;
	private String unitName;
	public long getId() {
		return id;
	}
	public long getPackagesMasterId() {
		return packagesMasterId;
	}
	public String getDuration() {
		return duration;
	}
	public String getUserInputs() {
		return userInputs;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public int getIsExtras() {
		return isExtras;
	}
	public String getWindowType() {
		return windowType;
	}
	public String getWindowText() {
		return windowText;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPackagesMasterId(long packagesMasterId) {
		this.packagesMasterId = packagesMasterId;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setUserInputs(String userInputs) {
		this.userInputs = userInputs;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setIsExtras(int isExtras) {
		this.isExtras = isExtras;
	}
	public void setWindowType(String windowType) {
		this.windowType = windowType;
	}
	public void setWindowText(String windowText) {
		this.windowText = windowText;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
