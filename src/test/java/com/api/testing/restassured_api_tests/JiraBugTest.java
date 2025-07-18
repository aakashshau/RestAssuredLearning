package com.api.testing.restassured_api_tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraBugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://mxaakash.atlassian.net/";
		String response = given().log().all()
		.header("Content-Type","application/json")
		.header("Authorization","Basic bXhhYWthc2hAZ21haWwuY29tOkFUQVRUM3hGZkdGMG1GMXM2Rk9NZER6ajlZVXYtX2ZEd3BuckE2SGxCY2YtbjYwQVl3UVNkVE9FYWZpWS1JbWlHajZiTFhEMzJ3SDJuR2lXMEowYTlSSV91SjlVcnlyVkJqZWcxMHpzU0RZTE14dVRabXNLQlUyX1R1M2p3emdfSDBKSjZIb05PSHRacXMzTnBtajl6NF9ybXBsbjBfaW9qdHhfZDBncFZXYm5wM25oVDVpeDJmND0wMTEwNkFFMw==")
		.body("{\r\n"
				+ "\"fields\": {\r\n"
				+ "   \"project\":\r\n"
				+ "   { \r\n"
				+ "      \"key\": \"SCRUM\"\r\n"
				+ "   },\r\n"
				+ "   \"summary\": \"issue buttons value is not displaying by rest assured \",\r\n"
				+ "   \"issuetype\": {\r\n"
				+ "      \"name\": \"Bug\"\r\n"
				+ "   }\r\n"
				+ "  }\r\n"
				+ "}")
		.when().post("/rest/api/3/issue")
		.then().log().all()
		.assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String id = js.getString("id");
		System.out.println(id);
		
		given().header("Content-Type","multipart/form-data; boundary=<calculated when request is sent>").pathParam("key", id)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic bXhhYWthc2hAZ21haWwuY29tOkFUQVRUM3hGZkdGMG1GMXM2Rk9NZER6ajlZVXYtX2ZEd3BuckE2SGxCY2YtbjYwQVl3UVNkVE9FYWZpWS1JbWlHajZiTFhEMzJ3SDJuR2lXMEowYTlSSV91SjlVcnlyVkJqZWcxMHpzU0RZTE14dVRabXNLQlUyX1R1M2p3emdfSDBKSjZIb05PSHRacXMzTnBtajl6NF9ybXBsbjBfaW9qdHhfZDBncFZXYm5wM25oVDVpeDJmND0wMTEwNkFFMw==")
		.multiPart("file",new File("C:\\Users\\Tierless_soul\\Desktop\\Screenshot (1).png"))
		.when().post("/rest/api/3/issue/{key}/attachments")
		.then().log().all()
		.assertThat().statusCode(200);
	}

}
