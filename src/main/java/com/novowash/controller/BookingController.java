package com.novowash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.novowash.Enums.RESPONSE_CODES;
import com.novowash.model.BookingDetails;
import com.novowash.model.ResponseObject;
import com.novowash.service.BookingDetailsService;
import com.novowash.utils.GenUtilities;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping(value = "/booking")
public class BookingController {
	
	@Autowired private BookingDetailsService bookingservice;

	@PreAuthorize ("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/getAll")
	private ResponseObject getAllBookingDetails() {
		  List<BookingDetails> obj =bookingservice.getAllBooking();
		return GenUtilities.getSuccessResponseObject(obj, RESPONSE_CODES.SUCCESS.getDescription(),
				RESPONSE_CODES.SUCCESS.getCode());
		
	}
}
