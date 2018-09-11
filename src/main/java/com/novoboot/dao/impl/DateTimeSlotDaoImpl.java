package com.novoboot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.DateTimeSlotDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;

@Repository
public class DateTimeSlotDaoImpl extends NovoJdbcTemplate implements DateTimeSlotDao {

	private static final Logger logger = Logger.getLogger(DateTimeSlotDaoImpl.class);
	
	@Override
	public void insertDateSlot(long serviceId, String newDate) {
		logger.info("insertDateSlot={}" + serviceId);
		String query = "INSERT INTO service_date_slot(service_master_id,date_slot,is_enable,created_on) VALUES (?,?,?,now())";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setLong(index++, serviceId);
				pstmt.setString(index++, newDate);
				pstmt.setInt(index++, STATUS.ACTIVE.ID);
				return pstmt;
			}
		}, keyHolder);
	}

	@Override
	public void truncateDateTimeSlot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void importCsvIntoServiceDate() {
		String loadQuery = "LOAD DATA LOCAL INFILE '" + "D://service_date_slot.csv"
				+ "' INTO TABLE demo_table FIELDS TERMINATED BY ','"
				+ " LINES TERMINATED BY '\n' (id, service_master_id, next_no_of_day_available, time_slot_duration , next_disabled_dates) ";
		getJdbcTemplate().execute(loadQuery);
	}

}
