package com.novoboot.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.BookingDetailsDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.BookingDetails;
import com.novoboot.model.DateTimeSlots;
import com.novoboot.model.TimeDurationMaster;

@Repository
public class BookingDetailsDaoImpl extends NovoJdbcTemplate implements BookingDetailsDao {
	
	private static final Logger logger = Logger.getLogger(BookingDetailsDaoImpl.class);
	
	private static final String GET_ALL_BOOKING = "select scm.cat_name as serviceCategory, sm.service_name as serviceName, se.* from booking_details se "
			 +"	inner join  service_m sm on sm.id = se.service_id and sm.status = ? "
			 +"	inner join  service_cat_m scm on scm.id = sm.service_cat_id and scm.status = ?";

	@Override
	public List<BookingDetails> getAllBooking() {
		logger.info("BookingDetailsDaoImpl getAllBooking() Fetching all booking start"); 
		List<BookingDetails> bookingList = getJdbcTemplate().query(GET_ALL_BOOKING, new BeanPropertyRowMapper<BookingDetails>(BookingDetails.class), STATUS.ACTIVE.ID, STATUS.ACTIVE.ID);
		logger.info("BookingDetailsDaoImpl getAllBooking() Fetching all booking end"); 
		return bookingList!=null ? bookingList: new ArrayList<BookingDetails>();
	}

	@Override
	public DateTimeSlots getServiceTimeSlot(long serviceId) {
		logger.info("Getting the time slots for service"); 
		String query= "select * from date_time_slots where sservice_master_id = ?";
		DateTimeSlots dateTimeSlotList = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<DateTimeSlots>(DateTimeSlots.class), serviceId);
		return dateTimeSlotList !=null ? dateTimeSlotList : null;
	}

	@Override
	public TimeDurationMaster getTimeDurationMaster(Long timeDurationId) {
		logger.info("getTimeDurationMaster for timeDurationId = "+timeDurationId); 
		String query= "select * from time_duration_m where id = ?";
		TimeDurationMaster timeDurationMaster = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<TimeDurationMaster>(TimeDurationMaster.class), timeDurationId);
		return timeDurationMaster !=null ? timeDurationMaster : null;
	}

}
