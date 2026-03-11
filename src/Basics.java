import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.ReusableMethods;

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
		// here we are chaining up given method that is what and all should be submitted
		// to API
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.addPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();

		// now when that is submit the API just chain it again as above
		// inside post we have give resource which concatenates with baseURI and do a
		// post call
		// now then to check status and response
		// use log().all() in input that is given and output that is then
		// equalTo is a Hamcrest package
		// we can validate in body
		// we can validate headers as well Ex: Server header to check if its coming from
		// correct server
		// instead of hardcoding payload create a package files and payload.java and
		// make a static method so that no Object is required to access that method
		// extract().response().asString() and keep it in an variable

		// Now Parse JSon , it will take input as a String and gives JSON
		System.out.println("-------------RESPONSE-----------------");
		System.out.println(response);
		System.out.println("-------------RESPONSE-----------------");

		//JsonPath js = new JsonPath(response); // for parsing Json // we used resuable method here
		JsonPath js = ReusableMethods.rawToJson(response);
		String place_id = js.getString("place_id"); // getString because placeid is a String
		System.out.println("PALCE_ID----->" + place_id);

		// Now put
		String expectedAddress = "70 winter walk, Cannada";
		String putResponse = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(Payload.putPlace(place_id, expectedAddress)).when()
				.put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).extract().response().asString();
		System.out.println("-------------PUT RESPONSE-----------------");
		System.out.println(putResponse);
		System.out.println("-------------PUT RESPONSE-----------------");

		// Now verify address updated // Since it is GET no body so no
		// header("Content-Type","application/json")
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
				.body("address", equalTo(expectedAddress)).extract().response().asString();
		System.out.println("-------------GET RESPONSE-----------------");
		System.out.println(getResponse);
		System.out.println("-------------GET RESPONSE-----------------");

		JsonPath js1 = ReusableMethods.rawToJson(getResponse);
		String actualAddress = js1.getString("address");
		System.out.println("ACTUAL ADDRESS -----> " + actualAddress);
		String latitude = js1.getString("location.latitude");
		String longitude = js1.getString("location.longitude");

		System.out.println("Latitude----->" + latitude);
		System.out.println("Longitude----->" + longitude);

		// to assert we have to use testng as we cannot use rest assured assert as we
		// are out of given() chaining

		Assert.assertEquals(actualAddress, expectedAddress, "Adressess does not match");
		Assert.assertEquals(latitude, "-38.383494", "Latitude does not match");
		Assert.assertEquals(longitude, "33.427362", "Longitude does not match");
		System.out.println("FINISHED");

	}
}
