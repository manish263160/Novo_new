package com.novowash.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.Enums.RESPONSE_CODES;
import com.novowash.model.ResponseObject;
import com.novowash.model.Signupdto;
import com.novowash.model.User;
import com.novowash.service.UserService;
import com.novowash.utils.GenUtilities;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
	
	@Autowired private UserService userservice;
	
	
	@RequestMapping(method=RequestMethod.POST,value="/signup")
	public ResponseObject signup(@Valid @RequestBody Signupdto signup, Errors errors) {
		if(errors.hasErrors()) {
			String errmsg= errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(","));
			return GenUtilities.getFailureResponseObject(errmsg,errmsg, RESPONSE_CODES.FAIL.getCode() ,RESPONSE_CODES.FAIL.getDescription());
		}
		User isUserExist = userservice.findByMobile(signup.getMobileNo());
		if(isUserExist == null) {
			
		}else {
			
//	----------------------send OTP to user		---------------------------------------
			return GenUtilities.getFailureResponseObject(RESPONSE_CODES.USER_EXIST.getMessage(),RESPONSE_CODES.USER_EXIST.getMessage(), RESPONSE_CODES.USER_EXIST.getCode() ,RESPONSE_CODES.USER_EXIST.getDescription());
		}
//		return userservice.getUserByUserName(loginObj.getUserName(), loginObj.getPassword(),loginObj.getDeviceid());
		return null;
	}	

}
