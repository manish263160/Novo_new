package com.novoboot.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.novoboot.Enums.BASIC_STRINGS;
import com.novoboot.Enums.CommonEnums.STATUS;
import com.novoboot.dao.UserDao;
import com.novoboot.dao.impl.UserAuthDaoImpl;
import com.novoboot.model.User;
import com.novoboot.service.UserAuthService;
import com.novoboot.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserAuthService userAuthService;

	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public User findUserByMobile(String mobileno) {
		return userDao.findUserByMobile(mobileno);
	}

	@Override
	@Transactional
	public User saveUser(String mobileNo, String otp) throws Exception {

		User usr = new User();
		try {
			usr.setMobileNo(mobileNo);
			if (!otp.isEmpty()) {
				usr.setOtp(Long.valueOf(otp));
			}
			usr.setName(BASIC_STRINGS.DEFAULT_USER.getStringName());
			usr.setStatus(STATUS.ACTIVE.ID);
			userDao.saveUser(usr);

			/**
			 * This part of vode with insert the user role in DB
			 */
			userDao.insertUserRole(usr.getId());
		} catch (DataAccessException e) {
			logger.error("DataAccessException and roleback =" + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception ====" + e.getMessage());
		}
		return usr;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByUserName(String userName, String password, String deviceId) {
		User user = userDao.getUserByUserName(userName, password);
		/*
		 * if (user != null && user.getId() > 0) { UserAuth userAuth = new UserAuth();
		 * userAuth.setUserName(userName); userAuth.setUserId(user.getId());
		 * userAuth.setDeviceId(deviceId);
		 * userAuth.setExpiryInMills(ApplicationConstants.EXPIRY_TIME_IN_MILLI_SEC);
		 * userAuth.setAuthToken(user.getToken());
		 * userAuthService.createUserAuth(userAuth); }
		 */
		return user;
	}

	@Override
	public List<String> getUserRoles(Long userId) {

		List<String> userRole = userDao.getUserRoles(userId);
		return userRole;
	}

	@Override
	public boolean insertPotentialCuastomer(String name, String email) {
		return userDao.insertPotentialCuastomer(name, email);
	}

}
