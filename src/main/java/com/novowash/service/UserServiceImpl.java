package com.novowash.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.novowash.dao.UserDao;
import com.novowash.model.User;
import com.novowash.model.UserAuth;
import com.novowash.utils.ApplicationConstants;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired	private UserDao userDao;
	@Autowired private UserAuthService userAuthService;

	
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByMobile(String mobileno) {
		return userDao.findByMobile(mobileno);
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

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
		/*if (user != null && user.getId() > 0) {
			UserAuth userAuth = new UserAuth();
			userAuth.setUserName(userName);
			userAuth.setUserId(user.getId());
			userAuth.setDeviceId(deviceId);
			userAuth.setExpiryInMills(ApplicationConstants.EXPIRY_TIME_IN_MILLI_SEC);
			userAuth.setAuthToken(user.getToken());
			userAuthService.createUserAuth(userAuth);
		}*/
		return user;
	}

	@Override
	public List<String> getUserRoles(Long userId) {
		
		List<String> userRole = userDao.getUserRoles(userId);
		return userRole;
	}

}
