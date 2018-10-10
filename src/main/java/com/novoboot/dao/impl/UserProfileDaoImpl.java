package com.novoboot.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.BASIC_STRINGS;
import com.novoboot.dao.UserProfileDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;

@Repository	
public class UserProfileDaoImpl extends NovoJdbcTemplate implements UserProfileDao {
	
	private static final Logger logger = Logger.getLogger(UserProfileDaoImpl.class);

	@Override
	public List<UserBookingDetails> getPreviousBookingService(int userId) {
		String query= "select * from user_booking_details where user_id = ? and booking_status = ?";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserBookingDetails>(UserBookingDetails.class) , userId , BASIC_STRINGS.COMPLETED.getStringName());
	}

	@Override
	public List<UserPackageBookingDetails> getPreviousPackageBookingService(int userId) {
		String query= "select * from user_package_booking_details where user_id = ? and booking_status = ?";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserPackageBookingDetails>(UserPackageBookingDetails.class) , userId , BASIC_STRINGS.COMPLETED.getStringName());
	}

}
