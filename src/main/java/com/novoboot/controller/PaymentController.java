package com.novoboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.ResponseObject;
import com.novoboot.service.PaymentService;
import com.novoboot.utils.ApplicationProperties;
import com.novoboot.utils.GenUtilities;
import com.novoboot.wraper.model.PaymentOrder;
import com.novoboot.wrapper.api.Instamojo;
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

	@RequestMapping(method = RequestMethod.POST, value = "/paymentRequest")
	private ResponseObject getRequest(@RequestBody PaymentOrder paymentOrder) {

		CreatePaymentOrderResponse createPaymentOrderResponse = null;
		InstamojoImpl.ClearInstance();
		
		logger.info("request param ::" + paymentOrder.toString());
		
		PaymentOrder order = new PaymentOrder();
		SimpleDateFormat sdf = new SimpleDateFormat("HH_ss_mm");
		order.setName(paymentOrder.getName());
		order.setEmail(paymentOrder.getEmail());
		order.setPhone(paymentOrder.getPhone());
		order.setCurrency(paymentOrder.getCurrency());
		order.setAmount(paymentOrder.getAmount());
		order.setDescription(paymentOrder.getDescription());
		order.setRedirectUrl(applicationProperties.getProperty("instamojo_redirecturl"));
		order.setWebhookUrl(applicationProperties.getProperty("instamojo_webhookurl"));
		order.setTransactionId(paymentOrder.getPhone() + "_" + sdf.format(new Date()));

		Instamojo api = paymentService.getApi();

		boolean isOrderValid = order.validate();
		if (isOrderValid) {
			createPaymentOrderResponse = paymentService.createNewPaymentOrder(api, order);
		}
		return GenUtilities.getSuccessResponseObject(createPaymentOrderResponse,
				RESPONSE_CODES.SUCCESS.getDescription(), RESPONSE_CODES.SUCCESS.getCode());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/webhookGet")
	private void getResponse() {
		logger.info("you are here webhook Get call ====");
	}
}