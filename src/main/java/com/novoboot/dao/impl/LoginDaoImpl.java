package com.novoboot.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.novoboot.dao.LoginDao;
import com.novoboot.jdbcTemplate.NovoJdbcTemplate;

@Repository
public class LoginDaoImpl extends NovoJdbcTemplate implements LoginDao {

	private static Logger logger = Logger.getLogger(LoginDaoImpl.class);
	
	@Override
	public void generateOtp(String mobileNo) {
		// TODO Auto-generated method stub
		
	}

}
