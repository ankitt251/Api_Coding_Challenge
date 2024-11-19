package com.hexaware.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.config.JwtProvider;
import com.hexaware.model.USER_ROLE;
import com.hexaware.model.User;
import com.hexaware.repository.UserRepository;
import com.hexaware.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private CustomUserDetailsService customUserServiceImpl;

	@Override
	public User createUser(User req) {
		User user = userRepository.findByEmail(req.getEmail());

		if (user == null) {
			User createdUser = new User();
			createdUser.setEmail(req.getEmail());
			createdUser.setRole(USER_ROLE.ROLE_USER);
			createdUser.setPassword(passwordEncoder.encode(req.getPassword()));

			user = userRepository.save(createdUser);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + USER_ROLE.ROLE_USER.toString()));

		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return user;
	}

	@Override
	public Map<String, String> loginUser(User req) {
		String email = req.getEmail();
		String password = req.getPassword();

		Authentication authentication = authenticate(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		Map<String, String> response = new HashMap<>();
		response.put("jwt", token);
		response.put("message", "Login successful!");

		return response;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserServiceImpl.loadUserByUsername(email);
		if (userDetails == null) {
			throw new BadCredentialsException("User not found");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password.....");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	}

}
