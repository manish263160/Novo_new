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
import com.novoboot.wraper.model.WebHookModel;

@Repository
public class PaymentDaoImpl extends NovoJdbcTemplate implements PaymentDao {

	private static final Logger logger = Logger.getLogger(PaymentDaoImpl.class);

	@Override
	public void inserstPaymentSuccessFull(WebHookModel webHookModel) {

		logger.info("paymentDaoimpl webhookmodel =" + webHookModel.toString());
		String sql = "INSERT INTO user_payment(user_id,amount,buyer,buyer_name,buyer_phone,currency,fees,longurl,mac,payment_id,payment_request_id,purpose,shorturl,status) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
				pstmt.setString(index++, webHookModel.getBuyerName());
				pstmt.setString(index++, webHookModel.getCurrency());
				pstmt.setString(index++, webHookModel.getFees());
				pstmt.setString(index++, webHookModel.getLongurl());
				pstmt.setString(index++, webHookModel.getMac());
				pstmt.setString(index++, webHookModel.getPaymentId());
				pstmt.setString(index++, webHookModel.getPaymentRequestId());
				pstmt.setString(index++, webHookModel.getPurpose());
				pstmt.setString(index++, webHookModel.getShorturl());
				pstmt.setString(index++, webHookModel.getStatus());
				return pstmt;
			}
		}, keyHolder);

	}

}
