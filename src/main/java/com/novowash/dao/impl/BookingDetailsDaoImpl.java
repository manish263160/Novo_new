package com.novowash.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novowash.Enums.CommonEnums.STATUS;
import com.novowash.dao.BookingDetailsDao;
import com.novowash.jdbcTemplate.NovoJdbcTemplate;
import com.novowash.model.BookingDetails;
import com.novowash.model.ServiceCategory;

@Repository
public class BookingDetailsDaoImpl extends NovoJdbcTemplate implements BookingDetailsDao {
	
	private static final Logger logger = Logger.getLogger(BookingDetailsDaoImpl.class);
	
	private static final String GET_ALL_BOOKING = "select scm.cat_name as serviceCategory, sm.service_name as serviceName, se.* from service_enquire se "
			 +"	inner join  service_m sm on sm.id = se.service_id and sm.status = ? "
			 +"	inner join  service_cat_m scm on scm.id = sm.service_cat_id and scm.status = ?";

	@Override
	public List<BookingDetails> getAllBooking() {
		logger.info("BookingDetailsDaoImpl getAllBooking() Fetching all booking start"); 
		List<BookingDetails> bookingList = getJdbcTemplate().query(GET_ALL_BOOKING, new BeanPropertyRowMapper<BookingDetails>(BookingDetails.class), STATUS.ACTIVE.ID, STATUS.ACTIVE.ID);
		logger.info("BookingDetailsDaoImpl getAllBooking() Fetching all booking end"); 
		return bookingList!=null ? bookingList: new ArrayList<BookingDetails>();
	}

}
