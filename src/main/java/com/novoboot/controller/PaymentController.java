package com.novoboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.BASIC_STRINGS;
import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.service.PaymentService;
import com.novoboot.service.UserService;
import com.novoboot.utils.ApplicationProperties;
import com.novoboot.utils.GenUtilities;
import com.novoboot.wrapper.api.InstamojoImpl;
import com.novoboot.wrapper.response.CreatePaymentOrderResponse;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/payment")
public class PaymentController {

	private static final Logger logger = Logger.getLogger(PaymentController.class);

	@Autowired
	ApplicationProperties applicationProperties;

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	UserService userService;
	

	@RequestMapping(method = RequestMethod.POST, value = "/paymentRequest")
	private ResponseObject paymentRequest(@RequestBody String paymentOrder) {

		CreatePaymentOrderResponse createPaymentOrderResponse = null;
		InstamojoImpl.ClearInstance();
		String frombooking =BASIC_STRINGS.SERVICE.getStringName();
		createPaymentOrderResponse = paymentService.userBooking(paymentOrder ,frombooking);
		
		return GenUtilities.getSuccessResponseObject(createPaymentOrderResponse,
				RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/paymentRequestPackage")
	private ResponseObject paymentRequestPackage(@RequestBody String paymentOrder) {

		CreatePaymentOrderResponse createPaymentOrderResponse = null;
		InstamojoImpl.ClearInstance();
		String frombooking =BASIC_STRINGS.PACKAGE.getStringName();
		createPaymentOrderResponse = paymentService.userBooking(paymentOrder , frombooking );
		
		return GenUtilities.getSuccessResponseObject(createPaymentOrderResponse,
				RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/details")
	private ResponseObject onPaymentSuccessHandler(@RequestParam(value="paymentId ") String paymentId , @RequestParam(value="status") String status) {
		logger.info("onPaymentSuccessHandler :: paymentId"+paymentId+" status ::"+status);
		boolean bool=paymentService.onPaymentSuccessFailerHandler(paymentId,status);
		return GenUtilities.getSuccessResponseObject(status,
				RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());
	}
	
	
}
