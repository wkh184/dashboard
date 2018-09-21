package com.nuhs.gcto.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="result_prediction")
public class ResultPrediction{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
		
	@Column(name="patient_mrn")
	private String patientMRN;
	
	@Column(name="result")
	private String result;
	
	@Column(name="result_description")
	private String resultDescription;
	
	@Column(name="predictor")
	private String predictor;
	
	@Column(name="dt_predicted")
	private Timestamp dtPredicted;

	public ResultPrediction(ResultPrediction resultPrediction) {
		this.id = resultPrediction.id;
		this.patientMRN = resultPrediction.patientMRN;
		this.result = resultPrediction.result;
		this.resultDescription = resultPrediction.resultDescription;
		this.predictor = resultPrediction.predictor;
		this.dtPredicted = resultPrediction.dtPredicted;
	}

	public ResultPrediction() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPatientMRN() {
		return patientMRN;
	}
	public void setPatientMRN(String patientMRN) {
		this.patientMRN = patientMRN;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	public String getPredictor() {
		return predictor;
	}
	public void setPredictor(String predictor) {
		this.predictor = predictor;
	}
	public Timestamp getDtPredicted() {
		return dtPredicted;
	}
	public void setDtPredicted(Timestamp dtPredicted) {
		this.dtPredicted = dtPredicted;
	}


}
