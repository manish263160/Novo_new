package com.novoboot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.novoboot.Enums.BASIC_STRINGS;
import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.UserProfileDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;
import com.novoboot.model.UserPackageTakenDates;

@Repository
public class UserProfileDaoImpl extends NovoJdbcTemplate implements UserProfileDao {

	private static final Logger logger = Logger.getLogger(UserProfileDaoImpl.class);

	@Override
	public List<UserBookingDetails> getPreviousBookingService(int userId) {
		String query = "select * from user_booking_details where user_id = ? and booking_status = ? OR payment_mode = ?";
		return getJdbcTemplate().query(query, new BeanPropertyRowMapper<UserBookingDetails>(UserBookingDetails.class),
				userId, BASIC_STRINGS.COMPLETED.getStringName() , BASIC_STRINGS.CASHON_DELIVERY.getStringName());
	}

	@Override
	public List<UserPackageBookingDetails> getPreviousPackageBookingService(int userId) {
		String query = "select * from user_package_booking_details where user_id = ? and booking_status = ?";
		return getJdbcTemplate().query(query,
				new BeanPropertyRowMapper<UserPackageBookingDetails>(UserPackageBookingDetails.class), userId,
				BASIC_STRINGS.COMPLETED.getStringName());
	}

	@Override
	public boolean updateUserAddress(String fromTable, JSONObject json) throws JSONException {

		int userId = json.getInt("userId");
		int id = json.getInt("id");
		String paymentRequestId = json.getString("paymentRequestId");
		String userAddress = json.getString("userAddress");
		if (json != null) {
			String updateQuery = null;
			if (fromTable.equals(BASIC_STRINGS.SERVICE.getStringName())) {
				updateQuery = "UPDATE user_booking_details SET user_address = ? WHERE id = ? AND payment_request_id = ? and user_id =?";
			} else if (fromTable.equals(BASIC_STRINGS.PACKAGE.getStringName())) {
				updateQuery = "UPDATE user_package_booking_details SET user_address = ? WHERE id = ? AND payment_request_id = ? and user_id =?";
			}
			int qq = getJdbcTemplate().update(updateQuery, userAddress, id, paymentRequestId, userId);
			if (qq > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean updateServiceDates(long userId, UserBookingDetails userBookingDetails) {

		if (userBookingDetails != null && !StringUtils.isEmpty(userBookingDetails.getBookingDate())
				&& !StringUtils.isEmpty(userBookingDetails.getBookingTime())) {
			String updateQuery = "update user_booking_details set booking_date = ?,  booking_time = ? where id= ? and payment_request_id = ? and user_id =?";
			int updatestatus = getJdbcTemplate().update(updateQuery, userBookingDetails.getBookingDate(),
					userBookingDetails.getBookingTime(), userBookingDetails.getId(), userBookingDetails.getPaymentRequestId() , userId);
			if (updatestatus > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Boolean insertPackageDateSlot(UserPackageBookingDetails request) {
		if (request != null && !StringUtils.isEmpty(request.getLastBookingDate())
				&& !StringUtils.isEmpty(request.getLastBookingTime())) {
			String updateQuery = "update user_package_booking_details set last_booking_date = ?,  last_booking_time = ? where id= ? and payment_request_id = ?";
			int updatestatus = getJdbcTemplate().update(updateQuery, request.getLastBookingDate(),
					request.getLastBookingTime(), request.getId(), request.getPaymentRequestId());
			if (updatestatus > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void insertIntoPackageTaken(UserPackageBookingDetails request) {
		logger.debug("Start insertIntoPackageTaken");
		String INSERT_SQL = "INSERT INTO user_package_taken_dates (user_package_booking_id,user_id,booking_date,booking_time,number_of_used) "
				+ " VALUES(?,?,?,?,? );";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL,
						PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, request.getId());
				pstmt.setLong(index++, request.getUserId());
				pstmt.setString(index++, request.getLastBookingDate());
				pstmt.setString(index++, request.getLastBookingTime());
				pstmt.setInt(index++, STATUS.ACTIVE.ID);
				return pstmt;
			}
		}, keyHolder);
	}

	@Override
	public List<UserPackageTakenDates> getUserPackageTaken(int id) {
		String query = "select * from user_package_taken_dates where user_package_booking_id = ?";
		List<UserPackageTakenDates> list = getJdbcTemplate().query(query,
				new BeanPropertyRowMapper<UserPackageTakenDates>(UserPackageTakenDates.class), id);
		return list;
	}

	@Override
	public Boolean updateUserDetails(long userId, String name, String email) {
		String updateQuery = "update user set name = ?,  email = ? where id= ? ";
		int updatestatus = getJdbcTemplate().update(updateQuery, name, email, userId);
		if (updatestatus > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserBookingDetails getServiceDetailsById(String detailFor, int id) {

		String query = "select * from user_booking_details where id = ?";
		return getJdbcTemplate().queryForObject(query,
				new BeanPropertyRowMapper<UserBookingDetails>(UserBookingDetails.class), id);
	}

	@Override
	public UserPackageBookingDetails getPackageDetailsById(String detailFor, int id) {
		String query = "select * from user_package_booking_details where id = ?";
		return getJdbcTemplate().queryForObject(query,
				new BeanPropertyRowMapper<UserPackageBookingDetails>(UserPackageBookingDetails.class), id);
	}

	@Override
	public void updatePackageTaken(UserPackageBookingDetails request) {
		String updateQuery = "update user_package_taken_dates set booking_date = ?,  booking_time = ? where user_package_booking_id= ? ";
		getJdbcTemplate().update(updateQuery, request.getLastBookingDate(), request.getLastBookingTime(), request.getId());
		
	}

}
