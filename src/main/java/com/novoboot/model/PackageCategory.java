package com.novoboot.model;

import java.io.Serializable;

public class PackageCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String packageName;
	private String scopes;
	private String ImageUrl;
	private String packageDesc;
	private int status;
	public long getId() {
		return id;
	}
	public String getPackageName() {
		return packageName;
	}
	public String getScopes() {
		return scopes;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public int getStatus() {
		return status;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPackageDesc() {
		return packageDesc;
	}
	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	}
}
