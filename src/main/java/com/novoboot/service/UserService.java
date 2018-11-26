package com.novoboot.service;

import java.util.List;

import com.novoboot.dtos.UserDto;
import com.novoboot.model.User;

public interface UserService {
	
	User findById(long id);
	
	User findUserByMobile(String mobileNo);
	
	User saveUser(String mobileNo , String otp) throws Exception;
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers(); 
	
	void deleteAllUsers();
	
	public boolean isUserExist(User user);

	User getUserByUserName(String userName, String password, String deviceId);

	List<String> getUserRoles(Long userId);

	boolean insertPotentialCuastomer(String name, String phone);
	
}
