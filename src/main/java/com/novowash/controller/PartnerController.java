package com.novowash.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.Enums.RESPONSE_CODES;
import com.novowash.model.Partner;
import com.novowash.model.ResponseObject;
import com.novowash.service.PartnerService;
import com.novowash.utils.GenUtilities;

@RestController
@RequestMapping(value = "/partner")
public class PartnerController {
	
	private static final Logger logger = Logger.getLogger(PartnerController.class);
	
	@Autowired  private PartnerService partnerService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public ResponseObject addPartner(@RequestBody Partner partner) {
		return GenUtilities.getSuccessResponseObject(partnerService.addPartner(partner), RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
	}

}
