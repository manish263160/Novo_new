package com.novoboot.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.novoboot.model.User;

@Repository
public interface UserDao {
User findUserByMobile(String mobileno);

void saveUser(User user);

void updateUser(User user);

void deleteUserById(long id);

List<User> findAllUsers();

void deleteAllUsers();

public boolean isUserExist(User user);

User getUserByUserName(String userName, String password);

List<String> getUserRoles(Long userId);
}
