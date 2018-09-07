package com.novoboot.model;

import java.io.Serializable;
import java.sql.Time;

public class ServiceTimeSlot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4628017291414273783L;
	private Long id;
	private String slotName;
	private String startTime;
	private String endTime;
	private Long isEnable;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSlotName() {
		return slotName;
	}
	public void setSlotName(String slotName) {
		this.slotName = slotName;
	}

	public Long getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Long isEnable) {
		this.isEnable = isEnable;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endtTime) {
		this.endTime = endtTime;
	}
	
}
