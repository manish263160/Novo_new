package com.novoboot.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.dao.UserProfileDao;
import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;
import com.novoboot.model.UserPackageTakenDates;
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
	public boolean updateUserAddress(String fromTable, JSONObject json) throws JSONException {

		return userProfileDao.updateUserAddress(fromTable, json);
	}

	@Override
	@Transactional
	public Boolean insertPackageDateSlot(UserPackageBookingDetails request) {

		List<UserPackageTakenDates> list = userProfileDao.getUserPackageTaken(request.getId());

		boolean bool = userProfileDao.insertPackageDateSlot(request);
		if (list.isEmpty()) { // that means there is no entry of this request ID
			if (bool) {
				userProfileDao.insertIntoPackageTaken(request);
				return true;
			}
		}else {
			if (bool) {
				userProfileDao.updatePackageTaken(request);
				return true;
			}
			
		}
		return false;
	}

	@Override
	public Boolean updateUserDetails(long userId, String name, String email) {
		// TODO Auto-generated method stub
		return userProfileDao.updateUserDetails(userId, name, email);
	}

	@Override
	public UserPackageBookingDetails getPackageDetailsById(String detailFor, int id) {
		return userProfileDao.getPackageDetailsById(detailFor, id);
	}

	@Override
	public UserBookingDetails getServiceDetailsById(String detailFor, int id) {
		return userProfileDao.getServiceDetailsById(detailFor, id);
	}

	@Override
	public Boolean updateServiceDates(long userId, UserBookingDetails userBookingDetails) {
		return userProfileDao.updateServiceDates(userId, userBookingDetails);
	}

}
