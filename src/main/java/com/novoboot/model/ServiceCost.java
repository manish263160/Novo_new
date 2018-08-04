package com.novoboot.model;

/**
 * 
 * @author mukeshks
 *
 */
public class ServiceCost extends BaseDto {
	
	private long id;
	private long serviceId;
	private String costDetails;
	private double 	price;
	private int status;
	
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
	 * @return the serviceId
	 */
	public long getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the costDetails
	 */
	public String getCostDetails() {
		return costDetails;
	}
	/**
	 * @param costDetails the costDetails to set
	 */
	public void setCostDetails(String costDetails) {
		this.costDetails = costDetails;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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

}
