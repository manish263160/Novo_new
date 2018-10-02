package com.novoboot.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.model.User;
import com.novoboot.service.UserService;
import com.novoboot.service.impl.BookingServicesImpl;
import com.novoboot.utils.GenUtilities;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userservice;

	@Autowired
	private BookingServicesImpl services;

	@RequestMapping(value = "/testSample", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List userlist = new ArrayList<User>();
		User user = new User(1, "manish", "vill-rambagh,post-dineshpur", "manish263160@gmail.com");
		userlist.add(user);
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/book") public
	 * ResponseObject bookOrEnquireService(@RequestBody String enquire) { //
	 * services.bookOrEnquireService(enquire); JSONObject json = null; try { json =
	 * new JSONObject(enquire); if (json != null) { String name =
	 * json.getString("name"); String email = json.getString("email"); String phone
	 * = json.getString("phone"); String currency = json.getString("currency");
	 * String amount = json.getString("amount"); String successUrl =
	 * json.getString("successUrl"); String failUrl = json.getString("failUrl");
	 * 
	 * JSONObject selectedServices = (JSONObject) json.get("selectedServices"); Long
	 * serviceCatId=
	 * ((JSONObject)selectedServices.get("mainService")).getLong("id"); Long
	 * serviceMasterId= ((JSONObject)
	 * selectedServices.get("subService")).getLong("id"); if (selectedServices !=
	 * null) { JSONArray mainPackages = null; JSONArray extraPackages = null;
	 * if(serviceCatId != 6) { mainPackages =
	 * selectedServices.getJSONArray("mainPackages"); extraPackages=
	 * selectedServices.getJSONArray("extraPackages"); Map<String, Integer>
	 * serviceComboPackage = new LinkedHashMap<>(); Map<String, Integer>
	 * extraservices = new LinkedHashMap<>(); GenUtilities.extracted(mainPackages,
	 * serviceComboPackage); GenUtilities.extracted(extraPackages, extraservices);
	 * 
	 * logger.info("serviceComboPackage===" + serviceComboPackage.toString());
	 * logger.info("extraservices===" + extraservices.toString()); }else {
	 * mainPackages = new JSONArray();
	 * mainPackages.put(selectedServices.getJSONArray("type"));
	 * mainPackages.put(selectedServices.getJSONArray("subType")); Map<String,
	 * Integer> serviceComboPackage = new LinkedHashMap<>();
	 * GenUtilities.extracted(mainPackages, serviceComboPackage); } } //
	 * logger.info("servicCat==="+cost);
	 * 
	 * 
	 * serviceMasterId= ((JSONObject) json.get("subService")).getLong("id");
	 * JSONArray mainPackages= (JSONArray) json.get("mainPackages"); JSONArray
	 * extraPackages= (JSONArray) json.get("extraPackages");
	 * 
	 * String type = json.getString("type"); String subType =
	 * json.getString("subType"); String totalAmount =
	 * json.getString("totalAmount"); String couponApplied =
	 * json.getString("couponApplied"); String userId = json.getString("user_id") !=
	 * null ? json.getString("user_id") : null; JSONObject dateUserDetails =
	 * (JSONObject)json.get("dateUserDetails"); String userAddress =
	 * dateUserDetails.getString("address"); String bookingDate=
	 * dateUserDetails.getString("date"); String bookingTime=
	 * dateUserDetails.getString("time"); JSONObject contactDetails =
	 * (JSONObject)dateUserDetails.get("contactDetails"); String userEmail =
	 * contactDetails.getString("email");
	 * 
	 * 
	 * UserBookingDetails userBookingDetails = new UserBookingDetails(userId,
	 * serviceCatId.longValue(), serviceMasterId.longValue(), serviceCostId,
	 * comboPackages, status, extraPackages, type, subtype, totalAmount,
	 * couponApplied, coupanCode, userAddress, pinCode, city, bookingDate,
	 * userEmail);
	 * 
	 * } } catch (JSONException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return
	 * GenUtilities.getSuccessResponseObject("Success",
	 * RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());
	 * 
	 * }
	 */

}
