package com.nuhs.gcto;


import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DiscoverySecurityConfig extends WebSecurityConfigurerAdapter {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

//	@Autowired
//	private UserDetailsService userDetailsService;

	@Autowired 
	DiscoveryAuthenticationProvider discoveryAuthenticationProvider;

	public DiscoverySecurityConfig() {
		super();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		logger.debug("configure");
		http
		.formLogin()
		.loginPage("/login.html")
		.failureUrl("/login-error.html")
		.and()
		.logout()
		.logoutUrl("/j_spring_security_logout")
		.logoutSuccessUrl("/index.html")
		.and()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasAuthority("USER")
		.antMatchers("/patient/**").hasAuthority("USER")
		.antMatchers("/corp/**").hasAuthority("CORP")
		.antMatchers("/shared/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/**").permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403.html");
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder)
			throws Exception {
//		DaoAuthenticationProvider authenticationProvider = new DiscoveryAuthenticationProvider();
//		authenticationProvider.setUserDetailsService(userDetailsService);
//		authenticationProvider.setPasswordEncoder(encoder());
		builder.authenticationProvider(discoveryAuthenticationProvider);
	}


	//	@Bean
	//	public UserDetailsService userDetailsService() {
	//		return new UserDetailsServiceImp();
	//	};
	//
	//	@Bean
	//	public BCryptPasswordEncoder passwordEncoder() {
	//		return new BCryptPasswordEncoder();
	//	};

	//	@Override
	//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	//	}

	//	@SuppressWarnings("deprecation")
	//	@Override
	//	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	//		auth
	//		.inMemoryAuthentication()
	//		.passwordEncoder(NoOpPasswordEncoder.getInstance())
	//		.withUser("jim").password("demo").roles("ADMIN").and()
	//		.withUser("bob").password("demo").roles("USER").and()
	//		.withUser("ted").password("demo").roles("USER","ADMIN");
	//	}

}
