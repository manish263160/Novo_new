package com.novoboot.model;

import java.io.Serializable;

public class PackagesMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3212314435129404453L;
	private long id;
	private long packageCatId;
	private String packageName;
	private String packageDesc;
	private String imageUrl;
	private int status;
	public long getId() {
		return id;
	}
	public long getPackageCatId() {
		return packageCatId;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getPackageDesc() {
		return packageDesc;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public int getStatus() {
		return status;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPackageCatId(long packageCatId) {
		this.packageCatId = packageCatId;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
