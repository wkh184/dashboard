package com.nuhs.gcto;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@PropertySource("classpath:ldap.properties")
@Profile("default")
public class DiscoveryLdapConfig {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private Environment env;

	private boolean ldapAvailable;

	public boolean isLdapAvailable() {
		return ldapAvailable;
	}

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		logger.debug("ldapAvailable = {}",env.getRequiredProperty("ldap.available"));
		ldapAvailable = Boolean.getBoolean(env.getRequiredProperty("ldap.available"));
		logger.debug("Url = {}",env.getRequiredProperty("ldap.url"));
		logger.debug("Base = {}",env.getRequiredProperty("ldap.partitionSuffix"));
		logger.debug("UserDn = {}",env.getRequiredProperty("ldap.principal"));
		logger.debug("Password = {}",env.getRequiredProperty("ldap.password"));
		contextSource.setUrl(env.getRequiredProperty("ldap.url"));
		contextSource.setBase(env.getRequiredProperty("ldap.partitionSuffix"));
		contextSource.setUserDn(env.getRequiredProperty("ldap.principal"));
		contextSource.setPassword(env.getRequiredProperty("ldap.password"));
		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}

	//    @Bean
	//    public LdapClient ldapClient() {
	//        return new LdapClient();
	//    }

}