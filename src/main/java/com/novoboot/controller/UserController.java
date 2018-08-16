package com.novoboot.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.model.Signupdto;
import com.novoboot.model.User;
import com.novoboot.service.UserService;
import com.novoboot.utils.GenUtilities;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("myBooking")
public class UserController {
	
	@Autowired private UserService userservice;
	
	

}
