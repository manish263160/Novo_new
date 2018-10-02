package com.novoboot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.PaymentDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.UserBookingDetails;
import com.novoboot.wraper.model.WebHookModel;

@Repository
public class PaymentDaoImpl extends NovoJdbcTemplate implements PaymentDao {

	private static final Logger logger = Logger.getLogger(PaymentDaoImpl.class);

	@Override
	public void inserstPaymentSuccessFull(WebHookModel webHookModel) {

		logger.info("paymentDaoimpl webhookmodel =" + webHookModel.toString());
		String sql = "INSERT INTO user_payment(user_id,amount,buyer,buyer_name,buyer_phone,currency,fees,longurl,mac,payment_id,payment_request_id,transaction_id,purpose,shorturl,status) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				// service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by
				pstmt.setLong(index++, webHookModel.getUserId());
				pstmt.setString(index++, webHookModel.getAmount());
				pstmt.setString(index++, webHookModel.getBuyer());
				pstmt.setString(index++, webHookModel.getBuyer_name());
				pstmt.setString(index++, webHookModel.getCurrency());
				pstmt.setString(index++, webHookModel.getFees());
				pstmt.setString(index++, webHookModel.getLongurl());
				pstmt.setString(index++, webHookModel.getMac());
				pstmt.setString(index++, webHookModel.getPayment_id());
				pstmt.setString(index++, webHookModel.getPayment_request_id());
				pstmt.setString(index++, webHookModel.getTransactionId());
				pstmt.setString(index++, webHookModel.getPurpose());
				pstmt.setString(index++, webHookModel.getShorturl());
				pstmt.setString(index++, webHookModel.getStatus());
				return pstmt;
			}
		}, keyHolder);

	}

	@Override
	public boolean insertUserBooking(UserBookingDetails userBookingDetails) {
		boolean returnbool = false;
		logger.info("userBookingDetails==="+userBookingDetails.toString());
		String query = "INSERT INTO user_booking_details (payment_request_id,transaction_id,user_id,consumer_email,service_cat_id,service_master_id,service_cost_id_list,"
				+ " service_cat_name,service_name,combo_packages,extra_packages,status,total_amount,coupon_applied,"
				+ " user_address,pin_code,city,booking_date,booking_time,consumer_name,consumer_phone,booking_status,created_on,"
				+ " created_by ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				// service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by
				pstmt.setString(index++, userBookingDetails.getPaymentRequestId());
				pstmt.setString(index++, userBookingDetails.getTransactionId());
				pstmt.setLong(index++, userBookingDetails.getUserId());
				pstmt.setString(index++, userBookingDetails.getConsumerEmail());
				pstmt.setLong(index++, userBookingDetails.getServiceCatId());
				pstmt.setLong(index++, userBookingDetails.getServiceMasterId());
				pstmt.setString(index++, userBookingDetails.getServiceCostIdList());
				pstmt.setString(index++, userBookingDetails.getServiceCatName());
				pstmt.setString(index++, userBookingDetails.getServiceName());
				pstmt.setString(index++, userBookingDetails.getComboPackages());
				pstmt.setString(index++, userBookingDetails.getExtraPackages());
				pstmt.setInt(index++, STATUS.INACTIVE.ID);
				pstmt.setDouble(index++, userBookingDetails.getTotalAmount());
				pstmt.setString(index++, userBookingDetails.getCouponApplied());
				pstmt.setString(index++, userBookingDetails.getUserAddress());
				pstmt.setInt(index++, userBookingDetails.getPinCode());
				pstmt.setString(index++, userBookingDetails.getCity());
				pstmt.setString(index++, userBookingDetails.getBookingDate());
				pstmt.setString(index++, userBookingDetails.getBookingTime());
				pstmt.setString(index++, userBookingDetails.getConsumerName());
				pstmt.setString(index++, userBookingDetails.getConsumerPhone());
				pstmt.setString(index++, userBookingDetails.getBookingStatus());
				pstmt.setString(index++, userBookingDetails.getConsumerName());
				return pstmt;
			}
		}, keyHolder);
		if(keyHolder != null && keyHolder.getKey().intValue() >= 0) {
			returnbool = true;
		}
		return returnbool;
	}

	@Override
	public void updateUserBookingDetails(String paymentId, String paymentReqstId, String orderStatus) {
		logger.info("paymentId==="+paymentId +" paymentReqstId==="+paymentReqstId+" orderStatus=="+orderStatus);
		String updateQuery = "update user_booking_details set payment_id= ?, booking_status =? where payment_request_id=?";
		int val= getJdbcTemplate().update(updateQuery, paymentId,orderStatus,paymentReqstId);
		
	}

}
