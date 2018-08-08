package com.novoboot.service;

import org.springframework.http.ResponseEntity;

public interface LoginServices {

	ResponseEntity<String> generateOtp(String mobileNo);

	ResponseEntity<String> verifyOtp(String mobile, String otp2);

	ResponseEntity<String> resendOtp(String mobileNo);

}
