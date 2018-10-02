package com.novoboot.dao;

import com.novoboot.model.UserBookingDetails;
import com.novoboot.wraper.model.WebHookModel;

public interface PaymentDao {

	void inserstPaymentSuccessFull(WebHookModel webHookModel);

	boolean insertUserBooking(UserBookingDetails userBookingDetails);

	void updateUserBookingDetails(String paymentId, String paymentReqstId, String orderStatus);

}
