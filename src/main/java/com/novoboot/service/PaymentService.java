package com.novoboot.service;

import com.novoboot.wraper.model.PaymentOrder;
import com.novoboot.wrapper.api.Instamojo;
import com.novoboot.wrapper.response.CreatePaymentOrderResponse;

public interface PaymentService {

	Instamojo getApi();

	CreatePaymentOrderResponse createNewPaymentOrder(Instamojo api, PaymentOrder order);

}
