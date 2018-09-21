package com.nuhs.gcto.dao;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultPredictionDAO {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
/*
	static public ResultPrediction getResult(String predictor, String mrn) {
		logger.debug("getResult");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List results = null;
		try {
			tx = session.beginTransaction();
			String hql = "FROM ResultPrediction R WHERE R.predictor = :predictor AND R.patientMRN = :mrn ORDER BY R.dtPredicted DESC";
			Query query = session.createQuery(hql);
			query.setParameter("predictor",predictor);
			query.setParameter("mrn",mrn);
			results = query.list();
			logger.debug("getResult size = {}", results.size());
			tx.commit();
		}catch (Exception e) {
			logger.error("getResult Exception", e);
			e.printStackTrace(); 
			if (tx!=null) {
				tx.rollback();
			}
		} finally {
			logger.debug("getResult close");
			session.close();
		}
		if(results.size() > 0) {
			return (ResultPrediction)results.get(0);
		}else {
			return new ResultPrediction();
		}
	}
*/
}
