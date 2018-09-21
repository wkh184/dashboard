package com.nuhs.gcto.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="result_response")
public class ResultResponse {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="result_id")
	private int result_id;
	
	@Column(name="adid")
	private String adid;
	
	@Column(name="result_response")
	private String resultResponse;
	
	@Column(name="predictor")
	private String predictor;
	
	@Column(name="dt_responded")
	private Timestamp dtResponded;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResult_id() {
		return result_id;
	}
	public void setResult_id(int result_id) {
		this.result_id = result_id;
	}
	public String getAdid() {
		return adid;
	}
	public void setAdid(String adid) {
		this.adid = adid;
	}
	public String getResultResponse() {
		return resultResponse;
	}
	public void setResultResponse(String resultResponse) {
		this.resultResponse = resultResponse;
	}
	public String getPredictor() {
		return predictor;
	}
	public void setPredictor(String predictor) {
		this.predictor = predictor;
	}
	public Timestamp getDtResponded() {
		return dtResponded;
	}
	public void setDtResponded(Timestamp dtResponded) {
		this.dtResponded = dtResponded;
	}

	
}
