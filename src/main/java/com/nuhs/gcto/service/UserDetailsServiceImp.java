package com.nuhs.gcto.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.stereotype.Service;

import com.nuhs.gcto.model.User;
import com.nuhs.gcto.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername");

		User user = findUserbyADID(username);

		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
			builder.roles(user.getRoles());
		} else {
			UsernameNotFoundException exception = new UsernameNotFoundException("User not found.");
			logger.error("loadUserByUsername Exception", exception);
			throw exception;
		}

		logger.debug("loadUserByUsername return");
		return builder.build();
	}

	public User findUserbyADID(String adid) {
		logger.debug("findUserbyADID");
		if(userRepository == null) {
			logger.debug("userRepository is null");
		}
		List users = userRepository.findByAdid(adid); 
		User user = (User) users.get(0);
		return user;
	}
}