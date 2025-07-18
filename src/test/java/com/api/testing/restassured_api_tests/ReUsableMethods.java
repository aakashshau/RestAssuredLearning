package com.api.testing.restassured_api_tests;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

	public static Object rawToJson;

	public static JsonPath rawToJson(String resp) {
		
		JsonPath js1 = new JsonPath(resp);
		return js1;

	}
}
