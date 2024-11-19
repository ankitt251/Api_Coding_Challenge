package com.hexaware.service;

import java.util.Map;

import com.hexaware.model.User;

public interface UserService {

	User createUser(User user);

	Map<String, String> loginUser(User user);

}
