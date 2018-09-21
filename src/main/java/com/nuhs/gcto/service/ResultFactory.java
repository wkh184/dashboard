package com.nuhs.gcto.service;

import com.nuhs.gcto.model.DpmResultPrediction;
import com.nuhs.gcto.model.DvtResultPrediction;
import com.nuhs.gcto.model.IResult;
import com.nuhs.gcto.model.ReadmResultPrediction;
import com.nuhs.gcto.model.ResultPrediction;

public class ResultFactory {

	final static public String DPM = "dpm";
	final static public String DVT = "dvt";
	final static public String READM = "readm";

	static public IResult getResult(String predictor, ResultPrediction resultPrediction) {
		if(predictor == null){
			return null;
		}		
		if(predictor.equalsIgnoreCase(DPM)){
			return new DpmResultPrediction(resultPrediction);
		} else if(predictor.equalsIgnoreCase(READM)){
			return new ReadmResultPrediction(resultPrediction);
		}else if(predictor.equalsIgnoreCase(DVT)){
			return new DvtResultPrediction(resultPrediction);
		}
		return null;		
	}
}
