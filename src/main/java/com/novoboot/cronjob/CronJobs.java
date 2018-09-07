package com.novoboot.cronjob;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.novoboot.service.BookingService;
import com.novoboot.service.DateTimeSlotService;

@Component
public class CronJobs {

	@Autowired
	DateTimeSlotService dateTimeSlotService;
	
	@Autowired
	BookingService bookingService;
	
	private static final Logger logger = Logger.getLogger(CronJobs.class);
	
//	@RequestMapping(method = RequestMethod.POST, value = "/insertDateSlot")
//	@Scheduled(cron = "0 0 23 * * *")
	@Scheduled(fixedRate =5000)
	public void insertDateSlot() {
	logger.info("--------------------------call task scheduler=======");
		try {
			
			dateTimeSlotService.importCsvIntoServiceDate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
