package com.novoboot.service.impl;

import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novoboot.service.PaymentService;
import com.novoboot.utils.ApplicationProperties;
import com.novoboot.wraper.model.PaymentOrder;
import com.novoboot.wrapper.api.Instamojo;
import com.novoboot.wrapper.api.InstamojoImpl;
import com.novoboot.wrapper.exception.ConnectionException;
import com.novoboot.wrapper.exception.InvalidPaymentOrderException;
import com.novoboot.wrapper.response.CreatePaymentOrderResponse;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static final Logger logger = Logger.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	/**
	 * This method will get the api related details with authtoken
	 */
	@Override
	public Instamojo getApi() {
		Instamojo api = null;
		try {
			// gets the reference to the instamojo api
			
			String clientId= applicationProperties.getProperty("instamojo_clientid");
			String clientSecret = applicationProperties.getProperty("instamojo_clientSecret");
			String apiEndPOint = applicationProperties.getProperty("instamojo_apiEndPoint");
			String authEndPoint = applicationProperties.getProperty("instamojo_authEndPoint");
			api = InstamojoImpl.getApi(clientId, clientSecret, apiEndPOint, authEndPoint);
		} catch (ConnectionException e) {
			logger.error(e.getMessage());
		}
		return api;
	}

	@Override
	public CreatePaymentOrderResponse createNewPaymentOrder(Instamojo api,PaymentOrder order) {
		try {
			CreatePaymentOrderResponse createPaymentOrderResponse = api.createNewPaymentOrder(order);
			// print the status of the payment order.
			logger.info("createPaymentOrderResponse :: "+createPaymentOrderResponse.getPaymentOrder().getStatus());
			return createPaymentOrderResponse;
		} catch (InvalidPaymentOrderException e) {
			logger.error(e.toString());

			if (order.isTransactionIdInvalid()) {
				logger.error("Transaction id is invalid. This is mostly due to duplicate transaction id.");
			}
			if (order.isCurrencyInvalid()) {
				logger.error("Currency is invalid.");
			}
		} catch (ConnectionException e) {
			logger.error( e.toString(), e);
		}
		return null;
	}

}
