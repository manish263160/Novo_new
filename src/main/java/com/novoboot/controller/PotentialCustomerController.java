package com.novoboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.service.UserService;
import com.novoboot.utils.GenUtilities;

@RestController
@RequestMapping(value ="/permitall")
public class PotentialCustomerController {

	private static final Logger logger = Logger.getLogger(PotentialCustomerController.class); 
	
	@Autowired
	UserService userService; 
	/**
	 * Insert the customer that is interested in novowash
	 * @param name name of customer
	 * @param email email of customer
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value ="/insertPotentialUser")
	private ResponseObject insertPotentialCuastomer(@RequestParam String name , @RequestParam String email){
		logger.debug("name :: email :: "+name +" "+email);
		boolean bool = userService.insertPotentialCuastomer(name,email);
		if(!bool) {
			return GenUtilities.getFailureResponseObject(bool, "inser into potential customer fail", RESPONSE_CODES.FAIL.getCode(), RESPONSE_CODES.FAIL.getDescription());
		}else {
			return GenUtilities.getSuccessResponseObject(bool, RESPONSE_CODES.SUCCESS.getDescription(),
					RESPONSE_CODES.SUCCESS.getCode());
		}
	}
}
