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
	private void getResponse(@RequestParam("id") String id ,@RequestParam("transaction_id") String transactionId ,
			@RequestParam("payment_id") String paymentId ,@RequestParam("status") String status , @RequestParam("amount") String amount , @RequestParam("mac") String mac ) {
		logger.info("WebHookModel ==id "+id +" transactionId"+transactionId+" paymentId="+paymentId+" status="+status+" amount="+amount+" mac="+mac);
		WebHookModel webHookModel= new WebHookModel();
		webHookModel.setPayment_request_id(id);
		webHookModel.setPayment_id(paymentId);
		webHookModel.setAmount(amount);
		webHookModel.setMac(mac);
		webHookModel.setStatus(status);
		webHookModel.setTransactionId(transactionId);
		paymentService.inserstPaymentSuccessFull(webHookModel);
		
	}
	
	
}
