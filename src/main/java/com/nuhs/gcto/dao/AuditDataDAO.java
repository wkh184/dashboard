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
public class AuditDataDAO {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT_AUDIT_DATA = "insert into audit_user(adid, mrn, dt_access, access_type) values(?,?,?,?)";

	@Autowired
	public AuditDataDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean createAuditData(String adid, String mrn, Timestamp dt_access, String access_type) {
		logger.debug("createAuditUser");
		return jdbcTemplate.update(SQL_INSERT_AUDIT_DATA, adid, mrn, dt_access, access_type) > 0;
	}

}
