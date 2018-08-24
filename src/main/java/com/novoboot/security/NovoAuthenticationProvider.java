package com.novoboot.security;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.novoboot.Enums.CommonEnums;
import com.novoboot.Enums.CommonEnums.USER_TYPE;
import com.novoboot.Enums.CommonEnums.UserRoleType;
import com.novoboot.exception.NovoWashException;
import com.novoboot.Enums.OTP_ENUMS;
import com.novoboot.Enums.RESPONSE_CODES;
import com.novoboot.model.User;
import com.novoboot.service.LoginServices;
import com.novoboot.service.UserService;
import com.novoboot.utils.LoginUtils;

/**
 * @author manish
 * 
 *         This Class is responsible for authentication and access control of
 *         users to cube root Admin module over http in extension of
 *         AuthenticationProvider interface of Spring web framework .
 *
 * 
 */
@Component("novoAuthenticationProvider")
public class NovoAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = Logger.getLogger(NovoAuthenticationProvider.class);

	@Autowired
	UserService userService;

	@Autowired
	LoginServices loginServices;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			logger.debug("ImageVideoAuthenticationProvider.authenticate() authentication.getPrincipal(): "
					+ authentication.getPrincipal());
			logger.debug("ImageVideoAuthenticationProvider.authenticate() authentication.getCredentials(): "
					+ authentication.getCredentials());

			String mobileNo = authentication.getPrincipal().toString();
			String otp = authentication.getCredentials().toString();

			ResponseEntity<String> responseEntity = loginServices.verifyOtp(mobileNo, otp);

			JsonNode jsonObj = LoginUtils.gebericResponseConvert(responseEntity);

			String getmessageFromJsonObj = jsonObj.get("message").asText();

			String getTypeFromJsonObj = jsonObj.get("type").asText();

			if (getmessageFromJsonObj.equalsIgnoreCase(OTP_ENUMS.INVALID_OTP.getKey()) || getmessageFromJsonObj.equalsIgnoreCase(OTP_ENUMS.OTP_NOT_VERIFIED.getKey())
					|| getmessageFromJsonObj.equalsIgnoreCase(OTP_ENUMS.MOBILE_NOT_FOUND.getKey())) {

				throw new RuntimeException(String.format(URLEncoder.encode(OTP_ENUMS.INVALID_OTP.getKey(), "UTF-8"),
						authentication.getPrincipal()));
			}

			else if (getmessageFromJsonObj.equalsIgnoreCase(OTP_ENUMS.OTP_VERIFIED.getKey())
					|| getmessageFromJsonObj.equalsIgnoreCase(OTP_ENUMS.ALREADY_VERIFIED.getKey())) {

				User user = userService.findUserByMobile(mobileNo);
				user.setMobileNo(mobileNo);
				user.setOTP(Long.parseLong(otp));

				if (user == null) {

					boolean bool= userService.saveUser(mobileNo);
					if(bool) {
					List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
					GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + UserRoleType.USER.getUserProfileType());
					
					grantList.add(authority);
					
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, otp, grantList);
					
					return token;
					}else {
						throw new NovoWashException(RESPONSE_CODES.FAIL.getDescription() , RESPONSE_CODES.FAIL.getCode(),RESPONSE_CODES.FAIL.getMessage());
					}
				} else {
					if (user != null && CommonEnums.STATUS.INACTIVE.ID == user.getStatus()) {
						throw new UsernameNotFoundException(String.format(
								URLEncoder.encode("You are not active", "UTF-8"), authentication.getPrincipal()));
					}

					if (user != null && CommonEnums.STATUS.BLOCK.ID == user.getStatus()) {
						throw new UsernameNotFoundException(
								String.format(URLEncoder.encode("You are blocked. Please contact admin", "UTF-8"),
										authentication.getPrincipal()));
					}
					List<String> roles = null;
					if (user != null) {
						roles = userService.getUserRoles(user.getId());
					}
					List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
					if (roles != null) {
						for (String role : roles) {
							// ROLE_USER, ROLE_ADMIN,..
							GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
							grantList.add(authority);
						}
					}
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, otp,
							grantList);
					return token;
				}
			}

			return null;
		} catch (Exception e) {
			logger.error("Error in ImageVideoAuthenticationProvider.authenticate()", e);
			throw new AuthenticationServiceException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.authentication.AuthenticationProvider#supports(
	 * java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}

}