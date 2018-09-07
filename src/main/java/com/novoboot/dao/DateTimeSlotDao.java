package com.novoboot.dao;

public interface DateTimeSlotDao {

	void insertDateSlot(long serviceId, String newDate);

	void truncateDateTimeSlot();

	void importCsvIntoServiceDate();

}
