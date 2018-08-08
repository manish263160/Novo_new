package com.novoboot.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.novoboot.dao.LoginDao;
import com.novoboot.service.LoginServices;
import com.novoboot.utils.LoginUtils;

@Service
public class LoginServicesImpl implements LoginServices {

	private static Logger logger = Logger.getLogger(LoginServicesImpl.class);

	@Autowired
	LoginUtils loginutils;

	/*
	 * @Value("${sms.url}") private String smsServiceUrl;
	 * 
	 * @Value("${sms.authkey}") private String smsauthkey;
	 * 
	 * @Value("${sms.otpMsgUrl}") private String otpMsgUrl;
	 * 
	 * @Value("${sms.otpResend}") private String otpResend;
	 * 
	 * @Value("${sms.otpVeryfy}") private String otpVeryfy;
	 */

	@Autowired
	LoginDao loginDao;

	@Override
	public ResponseEntity<String> generateOtp(String mobileNo) {
//			LoginUtils loginutils = new LoginUtils();
		ResponseEntity<String> response = loginutils.generateOtp(mobileNo);
		return response;
	}

	@Override
	public ResponseEntity<String> verifyOtp(String mobileNo, String otp) {
		ResponseEntity<String> response = loginutils.verifyOtp(mobileNo, otp);
		return response;
	}

	@Override
	public ResponseEntity<String> resendOtp(String mobileNo) {
		ResponseEntity<String> response = loginutils.resendOtp(mobileNo);
		return response;

	}

}
