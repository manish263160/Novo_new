package com.novoboot.dao;

import com.novoboot.model.UserBookingDetails;
import com.novoboot.model.UserPackageBookingDetails;
import com.novoboot.wraper.model.WebHookModel;

public interface PaymentDao {

	void inserstPaymentSuccessFull(WebHookModel webHookModel);

	void insertUserBooking(UserBookingDetails userBookingDetails);

	void updateUserBookingDetails(String paymentId, String paymentReqstId, String orderStatus);

	void updateUserBookingDetailsByPaymentId(String paymentId, String status);

	void updateUserPaymentTableByPaymentId(String paymentId, String status);

	UserPackageBookingDetails insertUserBookingPackage(UserPackageBookingDetails userPackageBookingDetails);

}
