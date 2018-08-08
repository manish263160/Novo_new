package com.novoboot.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.novoboot.Enums.OTP_ENUMS;
import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.service.LoginServices;
import com.novoboot.utils.GenUtilities;
import com.novoboot.utils.LoginUtils;

@RestController
@RequestMapping("/login")
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	LoginServices loginServices;

	/**
	 * This method id for get the OPT for new user.
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/generateOtp")
	public ResponseObject signup(@RequestParam(value = "mobileNo", required = true) String mobileNo) {

		logger.debug("mobile number got from the request" + mobileNo);
		ResponseObject returnObj = null;
		String regex = 	"((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}";
		Pattern p = Pattern.compile(regex);
		Matcher m= p.matcher(mobileNo);
		/*if (!m.matches()) {
			String validMobile = "Please provide a valid mobile number";
			return GenUtilities.getFailureResponseObject(validMobile, validMobile,
					RESPONSE_CODES.MOBILE_NUMBER_INVALID.getCode(),
					RESPONSE_CODES.MOBILE_NUMBER_INVALID.getDescription());
		} 
		  else*/ if (mobileNo.isEmpty() || mobileNo == null) {
			String emptyMobile = "Mobile number is empty";
			return GenUtilities.getFailureResponseObject(emptyMobile, emptyMobile,
					RESPONSE_CODES.MOBILE_NUMBER_EMPTY.getCode(), RESPONSE_CODES.MOBILE_NUMBER_EMPTY.getDescription());
		} else {

			ResponseEntity<String> responseEntity = loginServices.generateOtp(mobileNo);
			logger.info("responseEntity === " + responseEntity.hasBody());
//			return LoginUtils.gebericResponseConvert(responseEntity);
			JsonNode jsonObj = LoginUtils.gebericResponseConvert(responseEntity);
			
			String getmessageFromJsonObj = jsonObj.get("message").asText();
			String gettypeFromJsonObj = jsonObj.get("type").asText();
			if (RESPONSE_CODES.SUCCESS.getDescription().equalsIgnoreCase(gettypeFromJsonObj) && OTP_ENUMS.OTP_SENT_SUCCESSFUL.getKey().equalsIgnoreCase(getmessageFromJsonObj))
				return GenUtilities.getSuccessResponseObject(OTP_ENUMS.OTP_SENT_SUCCESSFUL.getKey(),getmessageFromJsonObj,
																RESPONSE_CODES.SUCCESS.getCode(), OTP_ENUMS.OTP_VLID_DURATION.getKey());
			else
				return GenUtilities.getFailureResponseObject(getmessageFromJsonObj, getmessageFromJsonObj,
						RESPONSE_CODES.OTP_POST_FAIL.getCode(), RESPONSE_CODES.OTP_POST_FAIL.getDescription());
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/resendOtp")
	public ResponseObject resendOtp(@RequestParam(value = "mobileNo", required = true) String mobileNo) {
		
		if (mobileNo.isEmpty() || mobileNo == null) {
			String emptyMobile = "Mobile number is empty";
			return GenUtilities.getFailureResponseObject(emptyMobile, emptyMobile,
					RESPONSE_CODES.MOBILE_NUMBER_EMPTY.getCode(), RESPONSE_CODES.MOBILE_NUMBER_EMPTY.getDescription());
		} else {

			ResponseEntity<String> responseEntity = loginServices.resendOtp(mobileNo);
			logger.info("responseEntity === " + responseEntity.hasBody());
//			return LoginUtils.gebericResponseConvert(responseEntity);
			JsonNode jsonObj = LoginUtils.gebericResponseConvert(responseEntity);
			
			String getmessageFromJsonObj = jsonObj.get("message").asText();
			String gettypeFromJsonObj = jsonObj.get("type").asText();
			if (RESPONSE_CODES.SUCCESS.getDescription().equalsIgnoreCase(gettypeFromJsonObj) && OTP_ENUMS.OTP_SENT_SUCCESSFUL.getKey().equalsIgnoreCase(getmessageFromJsonObj))
				return GenUtilities.getSuccessResponseObject(OTP_ENUMS.OTP_SENT_SUCCESSFUL.getKey(),getmessageFromJsonObj,
																RESPONSE_CODES.SUCCESS.getCode(), OTP_ENUMS.OTP_VLID_DURATION.getKey());
			else
				return GenUtilities.getFailureResponseObject(getmessageFromJsonObj, getmessageFromJsonObj,
						RESPONSE_CODES.OTP_POST_FAIL.getCode(), RESPONSE_CODES.OTP_POST_FAIL.getDescription());
		}
	}
}
