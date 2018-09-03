package com.novoboot.model;

import java.io.Serializable;
import java.util.List;

public class DateTimeSlots implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long serviceMasterId;
	private Long nextNoOfDayAvailable;
	private Long durationHrs;
	private Long timeDurationId;
	
	private List<String> dateList;
	private List<String> timeSlotsList;
	
	public Long getDurationHrs() {
		return durationHrs;
	}
	public void setDurationHrs(Long durationHrs) {
		this.durationHrs = durationHrs;
	}
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
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	public Long getTimeDurationId() {
		return timeDurationId;
	}
	public void setTimeDurationId(Long timeDurationId) {
		this.timeDurationId = timeDurationId;
	}
	
	
	}
