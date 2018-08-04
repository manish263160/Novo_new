package com.novoboot.model;

import java.util.List;

public class ServiceCategory extends BaseDto {
	private long id;
	private String catName;
	private String catDesc;
	private String imageUrl;
	private long catType; // 1 for booking type, 2 for enquiry
	private int status;
	private List<ServiceModel> services;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * @return the catDesc
	 */
	public String getCatDesc() {
		return catDesc;
	}
	/**
	 * @param catDesc the catDesc to set
	 */
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	
	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the services
	 */
	public List<ServiceModel> getServices() {
		return services;
	}
	/**
	 * @param services the services to set
	 */
	public void setServices(List<ServiceModel> services) {
		this.services = services;
	}
	public long getCatType() {
		return catType;
	}
	public void setCatType(long catType) {
		this.catType = catType;
	}
	
}
