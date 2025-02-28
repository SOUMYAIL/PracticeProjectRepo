package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String readDataFromJson(String key) throws Throwable, IOException, ParseException {
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader("./configAppData/commondata.json"));
		// convert java object in to json object using down casting
		JSONObject map = (JSONObject) obj;
		String data = (String) map.get(key);

		return data;

	}

}
