package com.novoboot.Enums;

public enum TIME_SLOT_ENUM {

	STOT_1("7 AM - 10 AM"),
	STOT_2("10 AM - 1 PM"),
	STOT_3("1 PM - 4 PM"),
	STOT_4("4 PM - 7 PM"),
	;
	private final String timeslot;
//	private final String value;

	private TIME_SLOT_ENUM(String timeslot) {
		this.timeslot = timeslot;
	}
	
	public String getTimeslot() {
		return timeslot;
	}

	@Override
	public String toString() {
		return timeslot + ": " ;
	}
}
