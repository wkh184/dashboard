package com.nuhs.gcto.dao;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuditUserDAO {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT_AUDIT_USER = "insert into audit_user(adid, action, dt_action) values(?,?,?)";

	@Autowired
	public AuditUserDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean createAuditUser(String adid, String action, Timestamp dt_action) {
		logger.debug("createAuditUser");
		return jdbcTemplate.update(SQL_INSERT_AUDIT_USER, adid, action, dt_action) > 0;
	}

}
