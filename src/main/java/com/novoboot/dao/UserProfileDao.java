package com.novoboot.dao;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;

public interface UserProfileDao {

	List<UserBookingDetails> getPreviousBookingService(int userId);

	List<UserPackageBookingDetails> getPreviousPackageBookingService(int userId);

	boolean updateUserAddress(String fromTable, JSONObject json) throws JSONException;

}
