package com.nuhs.gcto.model;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DpmResultPrediction  extends ResultPrediction implements IResult{
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public DpmResultPrediction(ResultPrediction resultPrediction) {
		super(resultPrediction);
	}

	@Override
	public Map getResultDescriptionPresentation() {
		logger.debug("getResultDescriptionPresentation");
		Map map = parseJSON(getResultDescription());
		return map;
	}

	private Map parseJSON(String json) {
		logger.error("parseJSON: {}", json);
		Map map = new LinkedHashMap<>();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(json);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray array = (JSONArray) jsonObject.get("prediction");
		    if (array != null) {
		        for (Object arrayObj : array) {
//					logger.debug("Obj = {}",arrayObj);
					JSONArray innerArray = (JSONArray)arrayObj;
					String key =  innerArray.get(0).toString();
					String value =  innerArray.get(1).toString();
//					logger.debug("Key = {} Value = {}", key, value);
					map.put(key,  value);
		        }
		    }
		}catch(ParseException pe){
			logger.error("position: {}", pe.getPosition());
			logger.error("parseJSON exception", pe);
		}	
		return map;
	}
}
