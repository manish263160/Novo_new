package com.novoboot.model;

import java.io.Serializable;

public class Partner extends BaseDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8022409817188055394L;
	private long id;
	private String email;
	private String partnerName;
	private long serviceMasterId;
	private String serviceName;
	private String contactNumber;
	
	public long getId() {
		return id;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public long getServiceMasterId() {
		return serviceMasterId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public void setServiceMasterId(long serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Partner [id=" + id + ", partnerName=" + partnerName + ", serviceMasterId=" + serviceMasterId
				+ ", serviceName=" + serviceName + ", contactNumber=" + contactNumber + "]";
	}
	
	
}
