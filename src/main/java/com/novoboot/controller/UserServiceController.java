package com.novoboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.model.ServiceEnquire;
import com.novoboot.service.UserServices;
import com.novoboot.utils.GenUtilities;

@RestController
@PreAuthorize ("hasRole('USER')")
@RequestMapping(value = "/services")
public class UserServiceController {

	@Autowired
	private UserServices services;
	
	@RequestMapping(method = RequestMethod.GET, value = "/serviceDetails")
	public ResponseObject getAllServicesDetails() {
		return GenUtilities.getSuccessResponseObject(services.getAllServicesDetails(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get/category")
	public ResponseObject getAllServiceCategories() {
		return GenUtilities.getSuccessResponseObject(services.getAllServiceCategories(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllService")
	public ResponseObject getAllCategory() {
		return GenUtilities.getSuccessResponseObject(services.getAllServices(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/get/{categoryId}")
	public ResponseObject getAllServiceByCatId(@PathVariable long categoryId) {
		return GenUtilities.getSuccessResponseObject(services.getAllServicsByCatId(categoryId), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get/cost/{serviceId}")
	public ResponseObject getServicesCostById(@PathVariable long serviceId) {
		return GenUtilities.getSuccessResponseObject(services.getServicesCostById(serviceId), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());

	}

	@RequestMapping(method = RequestMethod.POST, value = "/book")
	public ResponseObject bookOrEnquireService(@RequestBody ServiceEnquire enquire) {
		services.bookOrEnquireService(enquire);
		return GenUtilities.getSuccessResponseObject(enquire, RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());

	}

}
