package com.api.testing.restassured_api_tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

public class OAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type", "client_credentials")
				.when().post("oauthapi/oauth2/resourceOwner/token").then().log().all().assertThat().statusCode(200)
				.extract().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String accessTokan = js.getString("access_token");
		System.out.println(accessTokan);

		GetCourse gc = given().queryParams("access_token", accessTokan).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		gc.getCourses().getApi().get(1).getCourseTitle();
		
		List<Api> apiCourses =gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++ )
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		ArrayList<String> a = new ArrayList<String>();
		
		List<WebAutomation> webAutomationCourses=gc.getCourses().getWebAutomation();
		for(int i=0;i<webAutomationCourses.size();i++)
		{
			a.add(webAutomationCourses.get(i).getCourseTitle());
		}
		List<String>expectedList= Arrays.asList(courseTitles);
		Assert.assertEquals(a, expectedList);
		
		
		
	}

}
