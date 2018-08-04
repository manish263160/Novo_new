package com.novoboot.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author mukeshks
 *
 */
public class ServiceModel extends BaseDto {
	
	private long id;
	private long serviceCatId;
	private String serviceName;
	private String serviceDesc;
	private String imgageUrl;
	//private int serviceCat; //(1-Home, 0-office) 
	private int serviceType; //(Enquire-1, Book-0)
	private int status;
	private int serviceFor;  //(1- indevisual , 2- company)
	private List<ServiceCost> serviceCosts;
	
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
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the serviceDesc
	 */
	public String getServiceDesc() {
		return serviceDesc;
	}
	/**
	 * @param serviceDesc the serviceDesc to set
	 */
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	
	/**
	 * @return the imgageUrl
	 */
	public String getImgageUrl() {
		return imgageUrl;
	}
	/**
	 * @param imgageUrl the imgageUrl to set
	 */
	public void setImgageUrl(String imgageUrl) {
		this.imgageUrl = imgageUrl;
	}
	/**
	 * @return the serviceType
	 */
	public int getServiceType() {
		return serviceType;
	}
	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * @return the serviceCosts
	 */
	public List<ServiceCost> getServiceCosts() {
		if (serviceCosts == null) {
			serviceCosts = new ArrayList<ServiceCost>();
		}
		return serviceCosts;
	}
	/**
	 * @param serviceCosts the serviceCosts to set
	 */
	public void setServiceCosts(List<ServiceCost> serviceCosts) {
		this.serviceCosts = serviceCosts;
	}
	/**
	 * @return the serviceCatId
	 */
	public long getServiceCatId() {
		return serviceCatId;
	}
	/**
	 * @param serviceCatId the serviceCatId to set
	 */
	public void setServiceCatId(long serviceCatId) {
		this.serviceCatId = serviceCatId;
	}
	public int getServiceFor() {
		return serviceFor;
	}
	public void setServiceFor(int serviceFor) {
		this.serviceFor = serviceFor;
	}
	
}
