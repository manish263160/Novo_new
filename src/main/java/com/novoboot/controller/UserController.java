package com.novoboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.model.ServiceEnquire;
import com.novoboot.model.Signupdto;
import com.novoboot.model.User;
import com.novoboot.service.UserService;
import com.novoboot.service.impl.BookingServicesImpl;
import com.novoboot.utils.GenUtilities;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired private UserService userservice;
	
	@Autowired
	private BookingServicesImpl services;
	
	 @RequestMapping(value = "/testSample", method = RequestMethod.GET)
	    public ResponseEntity<List<User>> listAllUsers() {
	        List userlist = new ArrayList<User>();
		 User user= new User(1, "manish", "vill-rambagh,post-dineshpur", "manish263160@gmail.com");
		 userlist.add(user);
	        return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	    }

	 @RequestMapping(method = RequestMethod.POST, value = "/book")
		public ResponseObject bookOrEnquireService(@RequestBody ServiceEnquire enquire) {
			services.bookOrEnquireService(enquire);
			return GenUtilities.getSuccessResponseObject(enquire, RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());

		}
}
