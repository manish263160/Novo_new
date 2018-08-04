package com.novoboot.model;

public class BookingDetails extends ServiceEnquire {
	private String servicename;
	private String serviceCategory;

	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	

}
