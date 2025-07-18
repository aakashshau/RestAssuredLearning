package com.api.testing.restassured_api_tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class ECommerceAPITest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		RequestSpecification res = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("mxaakash@gmail.com");
		loginRequest.setUserPassword("Aakash@123");
		RequestSpecification reqLogin = given().log().all().spec(res).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login").then().log().all().extract()
				.response().as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		String token = loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String userId = loginResponse.getUserId();

		// Add Product
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
				.param("productName", "Formal shirt").param("productAddedBy", userId)
				.param("productCategory", "fashion").param("productSubCategory", "shirts")
				.param("productPrice", "11500").param("productDescription", "Addias Originals")
				.param("productFor", "women").multiPart("productImage",
						new File("C:\\Users\\Tierless_soul\\Desktop\\shirt.jpg"));
		
		File productImage = new File("C:\\Users\\Tierless_soul\\Desktop\\shirt.jpg");
		if (!productImage.exists()) {
		    System.out.println("❌ File not found: " + productImage.getAbsolutePath());
		    return;
		}

		String addProductResponse = reqAddProduct.when().post("api/ecom/product/add-product").then().log().all()
				.assertThat().statusCode(201).extract().response().asString();
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");
		System.out.println(productId);
	
	// Create Order
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).setContentType(ContentType.JSON).build();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productId);
		 	
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail> ();
		orderDetailList.add(orderDetail);
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		
	 RequestSpecification reqCreateOrder = given().log().all().spec(createOrderBaseReq).body(orders);
	 String ResponseAddOrder= reqCreateOrder.when().post("/api/ecom/order/create-order")
	 .then().log().all().assertThat().statusCode(201).extract().response().asString();	
	 System.out.println(ResponseAddOrder);
	 
	 // Delete all
	 RequestSpecification createDeleteBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			 .addHeader("authorization", token).build();
	 RequestSpecification reqDeleteOrder = given().log().all().spec(createDeleteBaseReq).pathParam("ProductId", productId);
	 String RespondDeleteOrder = reqDeleteOrder.when().delete("/api/ecom/product/delete-product/{ProductId}")
			 .then().log().all().assertThat().statusCode(200)
			 .extract().response().asString();
	 JsonPath js1 = new JsonPath(RespondDeleteOrder);
			 Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
	}
}
