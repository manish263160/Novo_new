package com.novoboot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ServiceDateSlot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long serviceMasterId;
	private Long nextNoOfDayAvailable;
	private Long timeSlotDuration;
	private String nextDisabledDates;
	private Long isEnable;
	private Date createOn;
	
	private List<String> timeSlotsList;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getServiceMasterId() {
		return serviceMasterId;
	}
	public void setServiceMasterId(Long serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}
	public Long getNextNoOfDayAvailable() {
		return nextNoOfDayAvailable;
	}
	public void setNextNoOfDayAvailable(Long nextNoOfDayAvailable) {
		this.nextNoOfDayAvailable = nextNoOfDayAvailable;
	}
	public List<String> getTimeSlotsList() {
		return timeSlotsList;
	}
	public void setTimeSlotsList(List<String> timeSlotsList) {
		this.timeSlotsList = timeSlotsList;
	}
	public Long getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Long isEnable) {
		this.isEnable = isEnable;
	}
	public Long getTimeSlotDuration() {
		return timeSlotDuration;
	}
	public void setTimeSlotDuration(Long timeSlotDuration) {
		this.timeSlotDuration = timeSlotDuration;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	public String getNextDisabledDates() {
		return nextDisabledDates;
	}
	public void setNextDisabledDates(String nextDisabledDateNumber) {
		this.nextDisabledDates = nextDisabledDateNumber;
	}
	
	
	}
