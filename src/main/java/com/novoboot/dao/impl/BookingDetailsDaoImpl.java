package com.novoboot.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.BookingDetailsDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.BookingDetails;
import com.novoboot.model.ServiceDateSlot;
import com.novoboot.model.ServiceTimeSlot;

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
		return bookingList!=null ? bookingList: Collections.emptyList();
	}

	@Override
	public ServiceDateSlot getServiceDateSlot(long serviceId) {
		logger.info("Getting the time slots for service"); 
		String query= "select * from service_date_slot where service_master_id = ?";
		ServiceDateSlot dateTimeSlotList = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<ServiceDateSlot>(ServiceDateSlot.class), serviceId);
		return dateTimeSlotList !=null ? dateTimeSlotList : null;
	}

	@Override
	public List<ServiceTimeSlot> getAllTImeSlot() {
		logger.info("getTimeDurationMaster for timeDurationId  "); 
		String query= "select * from service_time_slot";
		List<ServiceTimeSlot> timeDurationMaster = getJdbcTemplate().query(query, new BeanPropertyRowMapper<ServiceTimeSlot>(ServiceTimeSlot.class));
		return timeDurationMaster !=null ? timeDurationMaster : Collections.emptyList();
	}

	

}
