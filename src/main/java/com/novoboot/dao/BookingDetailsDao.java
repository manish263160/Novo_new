package com.novoboot.dao;

import java.util.List;

import com.novoboot.model.BookingDetails;
import com.novoboot.model.DateTimeSlots;
import com.novoboot.model.TimeDurationMaster;

public interface BookingDetailsDao {
	
	public List<BookingDetails> getAllBooking();

	public DateTimeSlots getServiceTimeSlot(long serviceId);

	public TimeDurationMaster getTimeDurationMaster(Long timeDurationId);

}
