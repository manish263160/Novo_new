package com.novoboot.dao;

import java.util.List;

import com.novoboot.model.BookingDetails;
import com.novoboot.model.ServiceDateSlot;
import com.novoboot.model.ServiceTimeSlot;

public interface BookingDetailsDao {

	public List<BookingDetails> getAllBooking();

	public ServiceDateSlot getServiceDateSlot(long serviceId);

	public List<ServiceTimeSlot> getAllTImeSlot();

}
