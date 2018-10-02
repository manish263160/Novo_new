package com.novoboot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.novoboot.Enums.BASIC_STRINGS;
import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.Enums.CommonEnums.USER_TYPE;
import com.novoboot.Enums.CommonEnums.BOOKINGSTATUS;
import com.novoboot.dao.UserDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;
import com.novoboot.model.User;

@Repository
public class UserDaoImpl extends NovoJdbcTemplate implements UserDao{
	private Logger logger = Logger.getLogger(UserDaoImpl.class);

	private static final String GET_USER = "select * from user where email = ? or mobile_no= ? and password = ?";
	private static final String GET_USER_BY_MOBILE = "select * from user where mobile_no = ? ";
	private static final String INSERT_SQL = "INSERT INTO user" + 
												"( mobile_no," + 
												"name," + 
												"status," + 
												"created_by )" + 
												"VALUES" + 
												"(?,?,?,?); ";
	

	@Override
	public User findUserByMobile(String mobileno) {

		User user = null;
		try {
			logger.info("sql ===" + GET_USER_BY_MOBILE);
			user = getJdbcTemplate().queryForObject(GET_USER_BY_MOBILE, new BeanPropertyRowMapper<User>(User.class), mobileno);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No user found" + e);
		} catch (DataAccessException e) {
			logger.error("Database exception found" + e);
		}
		return user;
	}

	@Override
	public void saveUser(User user) {

		logger.debug("Start saveUser");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setString(index++, user.getMobileNo());
				pstmt.setString(index++, user.getName());
				pstmt.setInt(index++, STATUS.ACTIVE.ID);
				pstmt.setString(index++,BASIC_STRINGS.SYSTEM.getStringName() );

				return pstmt;
			}
		}, keyHolder);
		user.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void updateUser(User user) {

	}

	@Override
	public void deleteUserById(long id) {

	}

	@Override
	public List<User> findAllUsers() {

		return null;
	}

	@Override
	public void deleteAllUsers() {

	}

	@Override
	public boolean isUserExist(User user) {

		return false;
	}

	@Override
	public User getUserByUserName(String userName, String password) {
		logger.info("Start login");
		User user = null;
		try {
			user = getJdbcTemplate().queryForObject(GET_USER, new BeanPropertyRowMapper<User>(User.class), userName,userName, password);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No user found" + e);
		} catch (DataAccessException e) {
			logger.error("No user found" + e);
		}
		return user;
	}

	@Override
	public List<String> getUserRoles(Long userId) {
		logger.debug("get user roles");
		List<String> userRoles= null;
		String query = "select r.name from user_roles  ur left join roles r on r.id = ur.role_id where ur.user_id = ? ";
		try {
			userRoles = getJdbcTemplate().queryForList(query ,String.class, userId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return userRoles;
	}

	@Override
	public void insertUserRole(long id) {
		String sql= "insert into user_roles(user_id , role_id) values (?,?)";
		int updt=getJdbcTemplate().update(sql, new Object[] {id, USER_TYPE.USER.ID});
		
	}

	@Override
	public boolean insertPotentialCuastomer(String name, String email) {

		String sql = "insert into potential_customers(name , email) values (?,?)";
		int updt = getJdbcTemplate().update(sql, new Object[] { name, email });
		if (updt > 0) {
			return true;
		}
		return false;
	}

}
