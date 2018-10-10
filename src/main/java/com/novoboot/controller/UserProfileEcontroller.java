package com.novoboot.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.novoboot.Enums.BASIC_STRINGS;
import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;
import com.novoboot.service.UserProfileService;


@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/userprofile")
public class UserProfileEcontroller {

	private static final Logger logger = Logger.getLogger(UserProfileEcontroller.class);

	@Autowired
	UserProfileService userProfileService;

	@RequestMapping(value = "/getPreviousBookingService/{detailFor}/{userId}", method = RequestMethod.GET)
	public ResponseEntity<?> getPreviousBookingService(@PathVariable("detailFor") String detailFor,
			@PathVariable("userId") int userId) {
		logger.info("detailFor==" + detailFor + "userId==" + userId);
		if (detailFor.equals(BASIC_STRINGS.SERVICE.getStringName())) {
			List<UserBookingDetails> bookingDetails = userProfileService.getPreviousBookingService(userId);
			return new ResponseEntity<List<UserBookingDetails>>(bookingDetails, HttpStatus.OK);
		} else if (detailFor.equals(BASIC_STRINGS.PACKAGE.getStringName())) {
			List<UserPackageBookingDetails> packageBookingDetails = userProfileService
					.getPreviousPackageBookingService(userId);
			return new ResponseEntity<List<UserPackageBookingDetails>>(packageBookingDetails, HttpStatus.OK);
		}
//		return null;
		return null;
	}

	@RequestMapping(value = "/updateServiceAddress/{service}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateAddress(@RequestBody String request,
			@PathVariable("service") String fromTable) {
		logger.info("request==" + request + " fromTable =" + fromTable);
		Boolean bool = false;
		try {
			JSONObject json = new JSONObject(request);
			bool = userProfileService.updateUserAddress(fromTable, json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Boolean>(bool, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/insertPackageDateSlot/{userID}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateAddress(@RequestBody String request,
			@PathVariable("service") String fromTable) {
		logger.info("request==" + request + " fromTable =" + fromTable);
		Boolean bool = false;
		try {
			JSONObject json = new JSONObject(request);
			bool = userProfileService.updateUserAddress(fromTable, json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Boolean>(bool, HttpStatus.OK);
	}*/

	
	
}
