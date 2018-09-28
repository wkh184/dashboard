package com.nuhs.gcto.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import com.nuhs.gcto.DiscoveryLdapConfig;
import com.nuhs.gcto.model.User;
import com.nuhs.gcto.repository.UserRepository;

@Service
public class UserService {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DiscoveryLdapConfig ldapConfig;

	private static final Integer THREE_SECONDS = 3000;

	@Autowired
	private LdapTemplate ldapTemplate;

	public UserService() {
		super();
	}

	public User findUserbyADID(String adid) {
		logger.debug("findUserbyADID");
		//		if(userRepository == null) {
		//			logger.debug("userRepository is null");
		//		}
		List users;
		if(ldapConfig.isLdapAvailable()) {
			users = getUserByADID(adid);
		}
		users = userRepository.findByAdid(adid); 
		User user = (User) users.get(0);
		return user;
	}

	private List<User> getUserByADID(String adid) {
		logger.debug("getUserByADID");

		SearchControls sc = new SearchControls();
		sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
		sc.setTimeLimit(THREE_SECONDS);
		sc.setCountLimit(10);
		//        sc.setReturningAttributes(new String[]{"cn"});
		sc.setReturningAttributes(null);

		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "user"));
		filter.and(new EqualsFilter("cn", adid));

		return ldapTemplate.search(LdapUtils.emptyLdapName(), filter.encode(), sc, new UserAttributesMapper());
	}


	/**
	 * Custom user attributes mapper, maps the attributes to the user POJO
	 */
	private class UserAttributesMapper implements AttributesMapper<User> {
		public User mapFromAttributes(Attributes attrs) throws NamingException {
			logger.debug("mapFromAttributes");
			User user = new User();
			//TODO check user ADID OU is not disabled
			//            person.setFullName((String)attrs.get("cn").get());
			printAttrs(attrs);
			Attribute ou = attrs.get("distinguishedName");
			if (ou != null){
				//                person.setLastName((String)sn.get());
				logger.debug("ou.get() {}", ou.get());
			}
			return user;
		}
	}

	public void printAttrs(Attributes attrs)
	{
		logger.debug(">> printAttrs()");
		if (attrs == null){
			logger.debug("No attributes");
		}else{
			// Print every single attribute
			try{
				for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();){
					Attribute attr = (Attribute) ae.next();
					logger.debug("Attribute ID: " + attr.getID());

					// Print values of current attribute
					NamingEnumeration e = attr.getAll();
					while(e.hasMore()){
						String value = e.next().toString();
						logger.debug("Value: " + value);
					}
				}
			} catch (NamingException e) { 
				e.printStackTrace(); 
				logger.error("Exception ", e);
			}
		}
		logger.debug("<< printAttrs()");
	}
}
