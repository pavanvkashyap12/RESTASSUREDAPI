package requestAndResponseSpecBuilders;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Desirialization {
	
	@Test
	public void desirializationTest() {
		
		String[] courseTiltles = {"Selenium Webdriver Java" ,"Cypress", "Protractor"};
		
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
		GetCourse gc  = given().log().all().queryParam("access_token",accessToken)
		.when().get("getCourseDetails")
		.then().log().all().extract().response().as(GetCourse.class);
		// Here instead of .as.String() we are doing .as(getCourse.class)
		
		// This is where de-sirialization is going on
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
	    System.out.println(gc.getCourses().getApi().get(0).getCourseTitle());
	    
	    List<API> courses = gc.getCourses().getApi();
	    for(int i=0;i<courses.size();i++) {
	    	if(courses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
	    		System.out.println("Course is " +  courses.get(i).getCourseTitle() + " and price is " + courses.get(i).getPrice());
	    		break;
	    	}
	    }
	    
	    // expected we are going with array as we know how many, ArrayList<> used in actual as it can be dynamic in runtime
	    ArrayList<String> actualCourseTitles = new ArrayList<String>();
	    List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation(); // if there are 2 WebAutomation class here use List<PojoClasses.WebAutomation> <package.Class> 
	    for(int i=0;i<webAutomationCourses.size();i++) {
	    	actualCourseTitles.add(webAutomationCourses.get(i).getCourseTitle());
	    }
		
	    // Comparing Array and ArrayList is hard. So convert Array into ArrayList using Arrays.asList()
	    List<String> expectedCourseTitles = Arrays.asList(courseTiltles);
	    Assert.assertEquals(actualCourseTitles, expectedCourseTitles);
	    Assert.assertTrue(expectedCourseTitles.equals(expectedCourseTitles));
	    
	    // Desirialization is used when we get response. JSON -> JAVA Object
	    // Serialization is used on body/payload -> Java Object to Json 
	    
	}
	
	

}
