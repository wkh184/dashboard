package com.nuhs.gcto;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nuhs.gcto.model.User;
import com.nuhs.gcto.service.UserDetailsServiceImp;

@Component
public class DiscoveryAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	//public class DiscoveryAuthenticationProvider implements AuthenticationProvider {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	UserDetailsServiceImp userService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		logger.debug("authenticate");
		String adid = authentication.getName();
		logger.debug("adid = {}", adid);
		Object credentials = authentication.getCredentials();
		logger.debug("credentials class: " + credentials.getClass());
		if (!(credentials instanceof String)) {
			return null;
		}
		String password = credentials.toString();

		//No password checking, just checking that ADID exists
		if(userService == null) {
			logger.debug("userService is null");
		}
		User user = userService.findUserbyADID(adid);

		if (user == null) {
			throw new BadCredentialsException("Authentication failed for " + adid);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		logger.debug("Roles = {}", user.getRoles());
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoles()));
		Authentication auth = new UsernamePasswordAuthenticationToken(adid, null, grantedAuthorities);
		logger.debug("Roles = {}", auth.getAuthorities());
		logger.debug("Authenticated = {}", auth.isAuthenticated());
		logger.debug("authenticate return");
		return auth;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.debug("additionalAuthenticationChecks");
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		logger.debug("retrieveUser");
		
		return null;
	}

}
