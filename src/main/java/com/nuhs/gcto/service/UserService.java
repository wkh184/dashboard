package com.nuhs.gcto.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuhs.gcto.model.User;
import com.nuhs.gcto.repository.UserRepository;

@Service
public class UserService {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private UserRepository userRepository;

	public UserService() {
		super();
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
