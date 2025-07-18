package com.api.testing.restassured_api_tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test (dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
	    RestAssured.baseURI = "http://216.10.245.166";

	    String response = given().log().all()
	        .header("Content-Type", "application/json")
	        .body(payload.Addbook(isbn,aisle))
	        .when().post("/Library/Addbook.php")
	        .then().log().all().assertThat().statusCode(200)
	        .extract().response().asString();
	    	
	    JsonPath js= ReUsableMethods.rawToJson(response);
	    String id=js.get("ID");
	    System.out.println(id);
	    
			String bookID = isbn + aisle;
			String Deleteresponse= given()
					.header("Content-Type","application/json")
					.body(payload.Addbook(isbn,aisle))
					.when().post("/Library/DeleteBook.php")
					.then().log().all()
					.assertThat().statusCode(200).extract().response().toString();
		   System.out.println("Deleted Book ID: " + bookID);
		   System.out.println("Delete Response: " + Deleteresponse);
		}
	  

	
	@DataProvider(name = "BooksData")
	public Object[][] getData()
	{
		//array =collection of elements
		//Multidimensional arrays = collection of arrays
		return new Object[][] {{"ts23f","33363"},{"s233d","5422"},{"rs23d23fe","54233"}};		
	}
	
}
