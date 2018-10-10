package com.novoboot.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.UserProfileDao;
import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;
import com.novoboot.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	private static final Logger logger = Logger.getLogger(UserProfileServiceImpl.class);
	
	@Autowired
	UserProfileDao userProfileDao;

	@Override
	public List<UserBookingDetails> getPreviousBookingService(int userId) {
		return userProfileDao.getPreviousBookingService(userId);
	}

	@Override
	public List<UserPackageBookingDetails> getPreviousPackageBookingService(int userId) {
		// TODO Auto-generated method stub
		return userProfileDao.getPreviousPackageBookingService(userId);
	}

	@Override
	public boolean updateUserAddress(String fromTable, JSONObject json) throws JSONException{
		
		return userProfileDao.updateUserAddress(fromTable, json) ;
	} 

}
