package com.novoboot.dao;

import java.util.List;

import com.novoboot.model.UserAuth;

public interface UserAuthDao {

	void insertUserAuth(UserAuth userAuth);

	boolean validateUserAuth(String authToken, String deviceId, String userName);

	void deleteUserAuth(List<Long> userIds);

	void updateUserAuthStatus(long userId, String deviceId);

}
