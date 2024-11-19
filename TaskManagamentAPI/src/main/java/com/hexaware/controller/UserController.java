package com.hexaware.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.exception.UserNotFoundException;
import com.hexaware.model.User;
import com.hexaware.serviceimpl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody User user) {
		try {
			User newUser = userService.createUser(user);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/signin")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody User req) throws UserNotFoundException {
		try {
			Map<String, String> loginResponse = userService.loginUser(req);
			return new ResponseEntity<>(loginResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
