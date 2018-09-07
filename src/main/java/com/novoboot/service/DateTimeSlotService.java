package com.novoboot.service;

public interface DateTimeSlotService {

	void insertDateSlot(long serviceId, String newDate);

	void truncateDateTimeSlot();

	void importCsvIntoServiceDate();

}
