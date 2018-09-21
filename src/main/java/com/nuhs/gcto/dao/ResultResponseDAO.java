package com.nuhs.gcto.dao;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultResponseDAO {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
/*
	static public Integer addResultResponse(ResultResponse response) {
		logger.debug("addResultResponse");
		Integer responseID = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			responseID = (Integer)session.save(response);
			tx.commit();
		}catch (Exception e) {
			logger.error("addResultResponse Exception", e);
			e.printStackTrace(); 
			if (tx!=null) {
				tx.rollback();
			}
		} finally {
			logger.debug("addResultResponse close");
			session.close();
		}
		return responseID;
	}
*/
}
