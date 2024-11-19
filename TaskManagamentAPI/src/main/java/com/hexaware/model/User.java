package com.hexaware.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Email
	private String email;

	// @Size(min = 6, message = "Password must be at least 6 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and be at least 6 characters long")
	private String password;

	private USER_ROLE role = USER_ROLE.ROLE_USER;

}
