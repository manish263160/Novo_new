package com.novowash.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.model.Signupdto;
import com.novowash.model.User;
import com.novowash.service.UserService;
import com.novowash.utils.GenUtilities;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userservice;
	
	@RequestMapping(method=RequestMethod.POST,value="/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody Signupdto signup, Errors errors) {
		if(errors.hasErrors()) {
			String errmsg= errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(","));
			return GenUtilities.GetErrorResponseEntity(errmsg);
		}
		User isUserExist = userservice.findByMobile(signup.getMobileNo());
//		return userservice.getUserByUserName(loginObj.getUserName(), loginObj.getPassword(),loginObj.getDeviceid());
		return null;
	}	

}
