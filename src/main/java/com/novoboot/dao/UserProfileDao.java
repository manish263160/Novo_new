package com.novoboot.dao;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;
import com.novoboot.model.UserPackageTakenDates;

public interface UserProfileDao {

	List<UserBookingDetails> getPreviousBookingService(int userId);

	List<UserPackageBookingDetails> getPreviousPackageBookingService(int userId);

	boolean updateUserAddress(String fromTable, JSONObject json) throws JSONException;

	Boolean insertPackageDateSlot(UserPackageBookingDetails request);

	void insertIntoPackageTaken(UserPackageBookingDetails request);

	List<UserPackageTakenDates> getUserPackageTaken(int id);

	Boolean updateUserDetails(long userId, String name, String email);

	UserPackageBookingDetails getPackageDetailsById(String detailFor, int id);

	UserBookingDetails getServiceDetailsById(String detailFor, int id);

	Boolean updateServiceDates(long userId, UserBookingDetails userBookingDetails);

	void updatePackageTaken(UserPackageBookingDetails request);

}
