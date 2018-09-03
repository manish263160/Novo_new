package com.novoboot.model;

import java.io.Serializable;
import java.sql.Time;

public class TimeDurationMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4628017291414273783L;
	private Long id;
	private String slotName;
	private Time startTime;
	private Time endtTime;
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
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndtTime() {
		return endtTime;
	}
	public void setEndtTime(Time endtTime) {
		this.endtTime = endtTime;
	}
	
}
