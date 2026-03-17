package PojoClasses;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import java.util.*;


public class Serialization {
	
	@Test
	public void test() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// SERIALIZATION
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setName("Frontline house");
		addPlace.setAddress("29, side layout,cohen 09");
		addPlace.setLanguage("French-IN");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("http://google.com");
		List<String> types = new ArrayList<String>();
		types.add("shoe");
		types.add("shop park");
		addPlace.setTypes(types);
		Location loc = new Location();
		loc.setLan(33.427362);
		loc.setLat(-38.383494);
		addPlace.setLocation(loc);
		// SERIALIZATION DONE , now pass this addPlace in body
		
		given().queryParam("key", "qaclick123")
		.body(addPlace).log().all()
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
	}
	

}
