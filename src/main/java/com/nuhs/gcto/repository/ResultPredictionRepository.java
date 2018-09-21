package com.nuhs.gcto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuhs.gcto.model.ResultPrediction;

@Repository
public interface ResultPredictionRepository extends CrudRepository<ResultPrediction, Long>{
	List<ResultPrediction> findByPatientMRNAndPredictorOrderByDtPredictedDesc(String predictor, String patientMrn);
}
