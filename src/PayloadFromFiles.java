import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class PayloadFromFiles {
	
	@Test
	public void payloadFromJsonFile() throws IOException {
		// first convert content of the file into Byte -> Byte is a datatype
		// then convert Byte to String
		// import a static package File
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		// here we are chaining up given method that is what and all should be submitted
		// to API
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				
				// convert to bytes , new String will convert to String
				// .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\eclipse-workspace\\Rest_Assured_API\\addPlace.json"))))
			    .body(bytesToString("C:\\\\Users\\\\Lenovo\\\\eclipse-workspace\\\\Rest_Assured_API\\\\addPlace.json"))
				.when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();
	}
	
	public static String bytesToString (String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
