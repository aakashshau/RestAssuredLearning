package com.api.testing.restassured_api_tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

public class Basics {

	public static void main(String[] args) throws IOException {
				// TODO Auto-generated method stub
				// Validate if Add Place API is working as expected
				
				//given - all input details
				//when  - Submit the API
				//Then  - validate the response
				//read your content of the file to String -> content of the file can convert into Byte -> Byte data to String
				
				RestAssured.baseURI = "https://rahulshettyacademy.com";
				String response = given().log().all().queryParam("key", "qaclick123").header(
						"Content-Type","application/json").body(new String (Files.readAllBytes(Paths.get("C:\\Users\\Tierless_soul\\eclipse-workspace\\restassured-api-tests\\addPlace.json")))).when().post("maps/api/place/add/json").then().log().all()
				.assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();	
				
				//Add place -> Update Place with New address -> Get Place to validate if New address is present in response 
					System.out.println(response);
					JsonPath js = ReUsableMethods.rawToJson(response);// for parsing Json
					String placeId=js.getString("place_id");
				System.out.println(placeId);
				
				//Update Place
				String newAddress = "Summer WalkStreet 20 East";
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+placeId+"\",\r\n"
						+ "\"address\":\""+newAddress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}\r\n"
						+ "").
				when().put("/maps/api/place/update/json")
				.then().log().all()
				.assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
				
				//Get Place
				String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId )
				.when().get("/maps/api/place/get/json").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
				JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
				String Actualaddress = js1.getString("address");
				System.out.println(Actualaddress);
				Assert.assertEquals(Actualaddress, newAddress);
				
				
				
				
				
				
				
				
			}	
}
