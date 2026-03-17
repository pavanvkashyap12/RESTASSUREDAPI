package requestAndResponseSpecBuilders;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import java.util.*;


public class SpecBuilderTest {
	
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
		
		
		// Request spec builder
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
		.setContentType(ContentType.JSON).build();
		
		// Response spec builder
		ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build(); // use this after then  
		
		RequestSpecification request = given().log().all().spec(req).body(addPlace); // This is full request on this object we do when and then which gives response
		Response response = request.when().post("maps/api/place/add/json")
		.then().log().all().spec(resp).extract().response();
		
		String res = response.asString();
		System.out.println(res);
	}
	

}
