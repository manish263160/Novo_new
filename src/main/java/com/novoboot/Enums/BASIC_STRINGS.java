package com.novoboot.Enums;

import java.io.Serializable;

public enum BASIC_STRINGS implements Serializable{

	SYSTEM("SYSTEM"),USER("USER"), DEFAULT_USER("DEFAULT_USER"), SERVICE("service") , PACKAGE("package"),
	INACTIVE("INACTIVE") , COMPLETED("Completed"),

	//These are the delivery options
	CASHON_DELIVERY("CASHON_DELIVERY"),ONLINE("ONLINE"),CANCELED("CANCELED");
	
	String stringName;

	BASIC_STRINGS(String stringName) {
		this.stringName = stringName;
	}
	public String getStringName() {
		return stringName;
	}

}
