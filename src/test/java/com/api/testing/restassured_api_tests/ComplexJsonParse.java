package com.api.testing.restassured_api_tests;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		
		JsonPath js =new JsonPath(payload.CoursePrice());
		//Print no. of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		//print purchase
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		//Print Title of the first course
		String titleFirstCourse=js.get("courses[0].title");
		System.out.println(titleFirstCourse);
		//Print All course titles and their respective Prices
		for(int i=0;i<count;i++)
		{
			String courseTitles = js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitles);	
		}
		
		System.out.println("Print no of copies sold by RPA course");
		//print no. of copies
		for(int i=0;i<count;i++)
		{
			String courseTitles = js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
		}
		
		//sum of all course matches with purchase amount
		int sum = 0;
		for(int i = 0;i<count;i++)
		{
			int courseCopies = js.get("courses["+i+"].copies");
			int coursePrice = js.get("courses["+i+"].price");
			sum = sum +(courseCopies*coursePrice);	
		}
		System.out.println(sum);
		Assert.assertEquals(sum, totalAmount);
	}

}
