package com.hexaware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

}
