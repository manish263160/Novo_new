package com.novoboot.mongodb.collection;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="CustomerReview")
public class CustomerReview implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7027945644843815270L;
	@Id
    private String id;
	private String customerName;
	private String customerImage;
	private String customerReview;
	private String socialNtwrkName;
	private String socialUrl;
	private Date createdOn;
	public String getId() {
		return id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getCustomerImage() {
		return customerImage;
	}
	public String getCustomerReview() {
		return customerReview;
	}
	public String getSocialNtwrkName() {
		return socialNtwrkName;
	}
	public String getSocialUrl() {
		return socialUrl;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setCustomerImage(String customerImage) {
		this.customerImage = customerImage;
	}
	public void setCustomerReview(String customerReview) {
		this.customerReview = customerReview;
	}
	public void setSocialNtwrkName(String socialNtwrkName) {
		this.socialNtwrkName = socialNtwrkName;
	}
	public void setSocialUrl(String socialUrl) {
		this.socialUrl = socialUrl;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
