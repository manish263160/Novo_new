package com.novoboot.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novoboot.dao.UserAuthDao;
import com.novoboot.model.UserAuth;

@Service
public class UserAuthService {

	private static final Logger logger = Logger.getLogger(UserAuthService.class);
	
	@Autowired private UserAuthDao userAuthDao;
	
	@Transactional(rollbackFor = Throwable.class)
	public void createUserAuth(UserAuth userAuth) {
		logger.info("Start UserAuthService:createUserAuth() for userName:"+userAuth.getUserName());
	    //changing status of auth entry
		userAuthDao.updateUserAuthStatus(userAuth.getUserId(), userAuth.getDeviceId());
		
		//insert new auth entry for user
		userAuthDao.insertUserAuth(userAuth);
	}
	
	public boolean validateUserAuth(String authToken, String deviceId, String userName) {
		return userAuthDao.validateUserAuth(authToken, deviceId, userName);
	}

	public void deleteUserAuth(List<Long> userIds) {
		userAuthDao.deleteUserAuth(userIds);
	}

}
