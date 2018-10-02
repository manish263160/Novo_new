package com.novoboot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.model.User;
import com.novoboot.service.PaymentService;
import com.novoboot.service.UserService;
import com.novoboot.utils.ApplicationProperties;
import com.novoboot.wraper.model.WebHookModel;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/permitall/paymentReceive")
public class PaymentReceiverController {
	private static final Logger logger = Logger.getLogger(PaymentReceiverController.class);

	@Autowired
	ApplicationProperties applicationProperties;

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/webhookGet" , consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	private void getResponse(@ModelAttribute WebHookModel webHookModel) {
		logger.info("WebHookModel ===="+webHookModel.toString());
		String userMobile = webHookModel.getBuyer_phone();
		if(userMobile != null) {
			if(userMobile.contains("+91")) {
				logger.info("mobile number contains +91");
				userMobile = userMobile.replaceAll("+91", "");
			}
			logger.info("mobile number "+userMobile);
				User user= userService.findUserByMobile(userMobile);
				if(user != null) {
					webHookModel.setUserId(user.getId());
					paymentService.inserstPaymentSuccessFull(webHookModel);
				}
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/paymentRedirectUrl")
	private void getpaymentRedirectUrl(@RequestParam(value="payment_request_id ") String payment_request_id , @RequestParam(value="payment_id") String payment_id) {
		logger.info("getpaymentRedirectUrl :: payment_request_id"+payment_request_id+" payment_id ::"+payment_id);
	}
}
