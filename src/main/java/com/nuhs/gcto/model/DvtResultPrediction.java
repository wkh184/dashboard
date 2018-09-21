package com.nuhs.gcto.model;

import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DvtResultPrediction extends ResultPrediction implements IResult{
	final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public DvtResultPrediction(ResultPrediction resultPrediction) {
		super(resultPrediction);
	}

	@Override
	public Map getResultDescriptionPresentation() {
		logger.debug("getResultDescriptionPresentation");
		Map map = parseJSON(getResultDescription());
		return map;
	}

	private Map parseJSON(String json) {
		Map map = new LinkedHashMap<>();
		JSONParser parser = new JSONParser();
		try{
			Object obj = parser.parse(json);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray array = (JSONArray) jsonObject.get("content");
		    if (array != null) {
		        for (Object arrayObj : array) {
//					logger.debug("Obj = {}",arrayObj);
					StringTokenizer st = new StringTokenizer((String)arrayObj, ":");
					String key = st.nextToken().trim();
					String value = st.nextToken().trim();
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
