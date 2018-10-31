package com.novoboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.Partner;
import com.novoboot.model.ResponseObject;
import com.novoboot.service.PartnerService;
import com.novoboot.utils.GenUtilities;

@RestController
@RequestMapping(value = "/permitall/partner")
public class PartnerController {
	
	private static final Logger logger = Logger.getLogger(PartnerController.class);
	
	@Autowired  private PartnerService partnerService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addPartner")
	public ResponseObject addPartner(@RequestBody Partner partner) {
		return GenUtilities.getSuccessResponseObject(partnerService.addPartner(partner), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}

}
