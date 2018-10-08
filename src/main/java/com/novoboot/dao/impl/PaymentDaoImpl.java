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
import com.novoboot.model.UserPackageBookingDetails;
import com.novoboot.wraper.model.WebHookModel;

@Repository
public class PaymentDaoImpl extends NovoJdbcTemplate implements PaymentDao {

	private static final Logger logger = Logger.getLogger(PaymentDaoImpl.class);

	@Override
	public void inserstPaymentSuccessFull(WebHookModel webHookModel) {

		logger.info("paymentDaoimpl webhookmodel =" + webHookModel.toString());
		String sql = "INSERT INTO user_payment(payment_request_id,user_id,amount,buyer,buyer_name,buyer_phone,mac,payment_id,status) "
				+ "VALUES (?,?,?,?,?,?,?,?,?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				// service_id,service_cost_id,service_date,house,landmark,locality,name,phone,email,status,created_on,created_by
				pstmt.setString(index++, webHookModel.getPayment_request_id());
				pstmt.setLong(index++, webHookModel.getUserId());
				pstmt.setDouble(index++, webHookModel.getAmount());
				pstmt.setString(index++, webHookModel.getBuyer());
				pstmt.setString(index++, webHookModel.getBuyer_name());
				pstmt.setString(index++, webHookModel.getBuyer_phone());
//				pstmt.setString(index++, webHookModel.getCurrency());
//				pstmt.setString(index++, webHookModel.getFees());
//				pstmt.setString(index++, webHookModel.getLongurl());
				pstmt.setString(index++, webHookModel.getMac());
				pstmt.setString(index++, webHookModel.getPayment_id());
//				pstmt.setString(index++, webHookModel.getTransactionId());
//				pstmt.setString(index++, webHookModel.getPurpose());
//				pstmt.setString(index++, webHookModel.getShorturl());
				pstmt.setString(index++, webHookModel.getStatus());
				return pstmt;
			}
		}, keyHolder);

	}

	@Override
	public void insertUserBooking(UserBookingDetails userBookingDetails) {
		boolean returnbool = false;
		logger.info("userBookingDetails==="+userBookingDetails.toString());
		String query = "INSERT INTO user_booking_details (payment_request_id,transaction_id,user_id,consumer_email,service_cat_id,service_master_id,service_cost_id_list,"
				+ " service_cat_name,service_name,combo_packages,extra_packages,status,total_amount,coupon_applied,"
				+ " user_address,pin_code,city,booking_date,booking_time,consumer_name,consumer_phone,booking_status,created_on,"
				+ " created_by ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?);";
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
		});
		
	}

	@Override
	public UserPackageBookingDetails insertUserBookingPackage(UserPackageBookingDetails userPackageBookingDetails) {
		UserPackageBookingDetails userBookingDetails= userPackageBookingDetails;
		logger.info("userBookingDetails==="+userBookingDetails.toString());
		String query = "INSERT INTO user_package_booking_details (payment_request_id,transaction_id,user_id,consumer_email,package_cat_id,package_master_id,package_cost_id_list,"
				+ " package_cat_name,package_name,combo_packages,extra_packages,total_amount,coupon_applied,"
				+ " user_address,pin_code,city,consumer_name,consumer_phone,booking_status,created_on,"
				+ " created_by ,expired_date ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),? , ?);";
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
				pstmt.setLong(index++, userBookingDetails.getPackageCatId());
				pstmt.setLong(index++, userBookingDetails.getPackageMasterId());
				pstmt.setString(index++, userBookingDetails.getPackageCostIdList());
				pstmt.setString(index++, userBookingDetails.getPackageCatName());
				pstmt.setString(index++, userBookingDetails.getPackageName());
				pstmt.setString(index++, userBookingDetails.getComboPackages());
				pstmt.setString(index++, userBookingDetails.getExtraPackages());
				pstmt.setDouble(index++, userBookingDetails.getTotalAmount());
				pstmt.setString(index++, userBookingDetails.getCouponApplied());
				pstmt.setString(index++, userBookingDetails.getUserAddress());
				pstmt.setInt(index++, userBookingDetails.getPinCode());
				pstmt.setString(index++, userBookingDetails.getCity());
				pstmt.setString(index++, userBookingDetails.getConsumerName());
				pstmt.setString(index++, userBookingDetails.getConsumerPhone());
				pstmt.setString(index++, userBookingDetails.getBookingStatus());
				pstmt.setString(index++, userBookingDetails.getConsumerName());
				pstmt.setString(index++, userBookingDetails.getExpiredDate());
				return pstmt;
			}
		} , keyHolder);
		userBookingDetails.setId(keyHolder.getKey().intValue());
		return userBookingDetails;
	}
	
	
	@Override
	public void updateUserBookingDetails(String paymentId, String paymentReqstId, String orderStatus) {
		logger.info("paymentId==="+paymentId +" paymentReqstId==="+paymentReqstId+" orderStatus=="+orderStatus);
		String updateQuery = "update user_booking_details set payment_id= ?, booking_status =? where payment_request_id=?";
		getJdbcTemplate().update(updateQuery, paymentId,orderStatus,paymentReqstId);
		
		String updateQuery1 = "update user_package_booking_details set payment_id= ?, booking_status =? where payment_request_id=?";
		getJdbcTemplate().update(updateQuery1, paymentId,orderStatus,paymentReqstId);
		
	}

	@Override
	public void updateUserBookingDetailsByPaymentId(String paymentId, String status) {
		logger.info("paymentId==="+paymentId+" Status=="+status);
		String updateQuery = "update user_booking_details set booking_status =? where payment_id=?";
		getJdbcTemplate().update(updateQuery, status,paymentId);
		
	}

	@Override
	public void updateUserPaymentTableByPaymentId(String paymentId, String status) {
		logger.info("paymentId==="+paymentId+" Status=="+status);
		String updateQuery = "update user_payment set status =? where payment_id=?";
		getJdbcTemplate().update(updateQuery, status,paymentId);
		
	}


}
