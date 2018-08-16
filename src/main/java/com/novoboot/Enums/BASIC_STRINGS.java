package com.novoboot.Enums;

import java.io.Serializable;

public enum BASIC_STRINGS implements Serializable{

	SYSTEM("SYSTEM"),USER("USER"), DEFAULT_USER("DEFAULT_USER");
	
	String stringName;

	BASIC_STRINGS(String stringName) {
		this.stringName = stringName;
	}
	public String getStringName() {
		return stringName;
	}

}
