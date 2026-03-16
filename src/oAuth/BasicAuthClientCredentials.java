package oAuth;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class BasicAuthClientCredentials {
	
	@Test
	public void getAccesToken () {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/oauthapi/";
		
		String responseAccessToken = given().log().all()
		.formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials")
		.formParams("scope","trust")
		.when().post("/oauth2/resourceOwner/token")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(responseAccessToken);
		String accessToken = js.getString("access_token");
		
		// Get Courses
		String courseDetails = given().log().all().queryParam("access_token",accessToken)
		.when().get("getCourseDetails")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("COURSE DETAILS ------> " + courseDetails);
		
	}

}
