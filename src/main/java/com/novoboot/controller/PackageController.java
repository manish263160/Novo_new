package com.novoboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.service.PackageService;
import com.novoboot.utils.GenUtilities;

@RestController
@RequestMapping(value = "/permitall/packages")
public class PackageController {

	private static final Logger logger = Logger.getLogger(PackageController.class);
	
	@Autowired
	PackageService packageService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/category")
	public ResponseObject getAllServiceCategories() {
		return GenUtilities.getSuccessResponseObject(packageService.getAllPackageCategories(), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getpackages/{packageCatId}")
	public ResponseObject getPackagesByCatId(@PathVariable long packageCatId) {
		return GenUtilities.getSuccessResponseObject(packageService.getPackagesByCatId(packageCatId), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getpackagecost/{packageMasterId}")
	public ResponseObject getPackageCost(@PathVariable long packageMasterId) {
		return GenUtilities.getSuccessResponseObject(packageService.getPackageCost(packageMasterId), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());

	}
}
