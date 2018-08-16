package com.novoboot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.CommonEnums;
import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.UserAuthDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.UserAuth;

@Repository
public class UserAuthDaoImpl extends NovoJdbcTemplate implements UserAuthDao {

	private static final Logger logger = Logger.getLogger(UserAuthDaoImpl.class);

	private static final String CREATE_USER_AUTH = "insert into user_auth(user_id,user_name,auth_token,device_id,expiry_in_mills,status,created_on, created_by) values(?,?,?,?,?,?, now(),?)";

	private static final String VALIDATE_AUTH_SQL = "select count(*) from t_user_auth "
	// +" where    created_on+((1*(expiryinmills/1000))/86400) >= ? and authtoken = ? and device_id = ? and status = ? and client_id = ?";
			+ " where  DATE_ADD(created_on, INTERVAL (expiryinmills/1000) second)>= ? and user_name = ? and authtoken = ? and device_id = ? and status = ?";

	private static final String UPDATE_STATUS_SQL = "update user_auth set status = now(), modified_on = ? where user_name = ? and device_id = ? and status = ? ";

	@Override
	public void insertUserAuth(UserAuth userAuth) {
		logger.debug("Start UserAuthDaoImpl:insertUserAuth()");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(CREATE_USER_AUTH , PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;

				pstmt.setLong(index++, userAuth.getUserId());
				pstmt.setString(index++, userAuth.getUserName());
				pstmt.setString(index++, userAuth.getAuthToken());
				pstmt.setString(index++, userAuth.getDeviceId());
				pstmt.setLong(index++, userAuth.getExpiryInMills());
				pstmt.setInt(index++, STATUS.ACTIVE.ID);
				pstmt.setString(index++, userAuth.getUserName());

				return pstmt;
			}
		}, keyHolder);
		userAuth.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void updateUserAuthStatus(long userId, String deviceId) {
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_STATUS_SQL);
				int index = 1;
				pstmt.setInt(index++, CommonEnums.STATUS.INACTIVE.ID);
				pstmt.setLong(index++, userId);
				pstmt.setString(index++, deviceId);
				pstmt.setLong(index++, CommonEnums.STATUS.ACTIVE.ID);
				return pstmt;
			}
		});

	}

	@Override
	public boolean validateUserAuth(String authToken, String deviceId, String userName) {
		int result = 0;
		result = getJdbcTemplate().queryForObject(VALIDATE_AUTH_SQL, Integer.class,
				new Object[] { userName, authToken, deviceId, CommonEnums.STATUS.ACTIVE.ID });
		return result > 0 ? true : false;
	}

	@Override
	public void deleteUserAuth(List<Long> userIds) {
		// TODO Auto-generated method stub

	}

}
