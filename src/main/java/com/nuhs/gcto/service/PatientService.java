package com.nuhs.gcto.service;


import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nuhs.gcto.dao.ResultPredictionDAO;
import com.nuhs.gcto.model.IResult;
import com.nuhs.gcto.model.LabResult;
import com.nuhs.gcto.model.Patient;
import com.nuhs.gcto.model.ReadmResultPrediction;
import com.nuhs.gcto.model.ResultPrediction;
import com.nuhs.gcto.repository.IssueRepository;
import com.nuhs.gcto.repository.ResultPredictionRepository;

@Service
public class PatientService {
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Autowired
	private ResultPredictionRepository resultPredictionRepository;


	public Model simulateLoadPatient(Model model) {
		Patient patient = new Patient();
		patient.setAttendingDoctor("Dr Ngiam");
		patient.setLocation("Ward 1");
		patient.setGender("Male");
		patient.setDob("1/1/1900");
		model.addAttribute("patient", patient);
		LabResult labResult = new LabResult();
		labResult.setLabResult("Positive");
		labResult.setLabTest("Blood test");
		labResult.setOrderDate("1/1/2018");
		model.addAttribute("labResult", labResult);
		ResultPrediction readmission = resultPredictionRepository.findByPatientMRNAndPredictorOrderByDtPredictedDesc("test1234","readm").get(0);
		IResult readmPresentator = ResultFactory.getResult(ResultFactory.READM, readmission);
		logger.debug("simulateLoadPatient result = {}", readmission.getResult());
		logger.debug("simulateLoadPatient result = {}", readmission.getResultDescription());
		model.addAttribute("readmission", readmPresentator);

		ResultPrediction dpm = resultPredictionRepository.findByPatientMRNAndPredictorOrderByDtPredictedDesc("test1234","dpm").get(0);
		IResult dpmPresentator = ResultFactory.getResult(ResultFactory.DPM, dpm);
		logger.debug("simulateLoadPatient result = {}", dpm.getResult());
		logger.debug("simulateLoadPatient result = {}", dpm.getResultDescription());
		model.addAttribute("dpm", dpmPresentator);

		ResultPrediction dvt = resultPredictionRepository.findByPatientMRNAndPredictorOrderByDtPredictedDesc("test1234","dvt").get(0);
		IResult dvtPresentator = ResultFactory.getResult(ResultFactory.DVT, dvt);
		logger.debug("simulateLoadPatient result = {}", dvt.getResult());
		logger.debug("simulateLoadPatient result = {}", dvt.getResultDescription());
		model.addAttribute("dvt", dvtPresentator);
		return model;

	}

}
