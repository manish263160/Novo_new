package com.novoboot.model;

/**
 * 
 * @author mukeshks
 *
 */
public class ServiceCost extends BaseDto {
	
	private long id;
	private long serviceMasterId;
	private String userInputs;
	private int unitPrice;
	private int isExtras;
	private int windowType;
	private int status;
	private String windowText;
	private String unitName;
	public long getId() {
		return id;
	}
	public long getServiceMasterId() {
		return serviceMasterId;
	}
	public String getUserInputs() {
		return userInputs;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public int getIsExtras() {
		return isExtras;
	}
	public int getWindowType() {
		return windowType;
	}
	public int getStatus() {
		return status;
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
	public void setServiceMasterId(long serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}
	public void setUserInputs(String userInputs) {
		this.userInputs = userInputs;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setIsExtras(int isExtras) {
		this.isExtras = isExtras;
	}
	public void setWindowType(int windowType) {
		this.windowType = windowType;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setWindowText(String windowText) {
		this.windowText = windowText;
	}
	public void setUnitName(String umitName) {
		this.unitName = umitName;
	}
	

}
