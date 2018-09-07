package com.novoboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.DateTimeSlotDao;
import com.novoboot.service.DateTimeSlotService;

@Service
public class DateTimeSlotServiceImpl implements DateTimeSlotService {
	
	@Autowired
	DateTimeSlotDao dateTimeSlotDao;

	@Override
	public void insertDateSlot(long serviceId, String newDate) {
		dateTimeSlotDao.insertDateSlot(serviceId, newDate);
		
	}

	@Override
	public void truncateDateTimeSlot() {
		dateTimeSlotDao.truncateDateTimeSlot() ;
		
	}

	@Override
	public void importCsvIntoServiceDate() {
		dateTimeSlotDao.importCsvIntoServiceDate() ;
		
	}

}
