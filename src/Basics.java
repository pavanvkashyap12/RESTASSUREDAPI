import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.Payload;


public class Basics {
	
	public static void main(String[] args) {
		
		// Validate if Add Place API is working as expected
		// import RestAssured and static import
		// in pom.xml remove scope<test> or else it will not work in main method
		
//		String payload = "{\r\n"
//				+ "  \"location\": {\r\n"
//				+ "    \"lat\": -38.383494,\r\n"
//				+ "    \"lng\": 33.427362\r\n"
//				+ "  },\r\n"
//				+ "  \"accuracy\": 50,\r\n"
//				+ "    \"name\": \"Frontline house\",\r\n"
//				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
//				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
//				+ "  \"types\": [\r\n"
//				+ "    \"shoe park\",\r\n"
//				+ "    \"shop\"\r\n"
//				+ "  ],\r\n"
//				+ "  \"website\": \"http://google.com\",\r\n"
//				+ "  \"language\": \"French-IN\"\r\n"
//				+ "}";
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		// here we are chaining up given method that is what and all should be submitted to API
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)");
			
		// now when that is submit the API just chain it again as above
		// inside post we have give resource which concatenates with baseURI and do a post call
		// now then to check status and response
		// use log().all() in input that is given and output that is then
		// equalTo is a Hamcrest package
		// we can validate in body
		// we can validate headers as well Ex: Server header to check if its coming from correct server
		
		// instead of hardcoding payload create a package files and payload.java and make a static method so that no Object is required to access that method
	}
}
