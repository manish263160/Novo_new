package com.novoboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.BookingDetailsDao;
import com.novoboot.model.BookingDetails;

@Service
public class BookingDetailsService {
	
	@Autowired private BookingDetailsDao bookingDao;
	
	public List<BookingDetails> getAllBooking() {
		return bookingDao.getAllBooking();
		
	}

}
