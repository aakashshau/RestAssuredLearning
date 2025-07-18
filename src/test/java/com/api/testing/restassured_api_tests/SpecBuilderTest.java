package com.api.testing.restassured_api_tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setPhone_number("(+91) 983 893 3937");
		p.setName("Sita Niwas");
		p.setWebsite("https://rahulshettyacademy.com");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		
		RequestSpecification req = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key","qaclick123")
		.setContentType(ContentType.JSON)
		.build();
		
		ResponseSpecification resspec= new ResponseSpecBuilder()
		.expectStatusCode(200).expectContentType(ContentType.JSON)
		.build();
		
		RequestSpecification res=given().spec(req).log().all().body(p);
		Response response = res.when().post("maps/api/place/add/json")
		.then().spec(resspec).extract().response();
		String responseString = response.asString();
		System.out.println(responseString);
	}

}
