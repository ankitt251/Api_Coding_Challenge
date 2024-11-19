package com.hexaware.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.model.USER_ROLE;
import com.hexaware.model.User;
import com.hexaware.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);

		if (user != null) {
			return buildUserDetails(user.getEmail(), user.getPassword(), user.getRole());
		}
		throw new UsernameNotFoundException("User not found with email: " + email);
	}

	private UserDetails buildUserDetails(String email, String password, USER_ROLE role) {
		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority(role.toString()));
		return new org.springframework.security.core.userdetails.User(email, password, authorityList);

	}
}
