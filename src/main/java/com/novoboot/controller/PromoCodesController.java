package com.novoboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.service.PromocodeService;
import com.novoboot.utils.GenUtilities;

@RestController
@RequestMapping("/promocodes")
public class PromoCodesController {
	
	private static final Logger logger = Logger.getLogger(PromoCodesController.class); 
	
	@Autowired
	PromocodeService promocodeService; 
	
	@RequestMapping(method = RequestMethod.GET, value ="/forservice/{serviceId}")
	private ResponseObject getPromocodesForService(@PathVariable long serviceId){
		return GenUtilities.getSuccessResponseObject(promocodeService.getPromocodeById(serviceId), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}

}
