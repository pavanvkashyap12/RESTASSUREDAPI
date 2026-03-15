package Jira;

import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;


public class BasicJiraAuthentication {
	
	// Basic Auth using rest api 
	// Jira allows it using API token
	// convert token into base64 while sending it to headers
	
	@Test
	public void basicAuth() {
		RestAssured.baseURI="https://pavankashyap1210.atlassian.net/";
		
		// CREATE ISSUE
		String createIssueResponse = given().log().all()
		.header("Content-Type", "application/json")
		.header("Authorization"," Basic cGF2YW5rYXNoeWFwMTIxMEBvdXRsb29rLmNvbTpBVEFUVDN4RmZHRjBQUlItTVpTS04tcFBiOHdYS1c5d0pmRlh6U1U5WGNnUzRkNjRzNXEzRElLT2d5SXlXZmlZODZrTGpseDdCQ2w2cUVSYUw0Y1lhR2sweEhMWllJVzB2WWRJZU1kSEVIZUdzRGdMbFJDeUFNUUYxX0R5LUNRMDFmSVBWcG94MnVwVm5nbUI2Q191bHl0cFJJZWowR04ybTZKVndmU1NiNHNpWjZfWFdtYkNpVzA9ODQwQkY5Qjg=")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"SCRUM\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"This is the issue created by Rest API.\",\r\n"
				+ "        \"description\": {\r\n"
				+ "            \"type\": \"Bug\",\r\n"
				+ "            \"version\": 1,\r\n"
				+ "            \"content\": [\r\n"
				+ "                {\r\n"
				+ "                    \"type\": \"paragraph\",\r\n"
				+ "                    \"content\": [\r\n"
				+ "                        {\r\n"
				+ "                            \"text\": \"This is the issue created by Rest API\",\r\n"
				+ "                            \"type\": \"Bug\"\r\n"
				+ "                        }\r\n"
				+ "                    ]\r\n"
				+ "                }\r\n"
				+ "            ]\r\n"
				+ "        },\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}\r\n"
				+ "")
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);
		String issueID = js.getString("id");
		System.out.println("ISSUE ID ----> " + issueID);
		
		// ADD ATTACHMENT TO THE ISSUE
		String addAttachmentResponse  = given().log().all()
		.pathParam("key", issueID) // no content-type application/json because we are sending it a multipart
		.header("Authorization","Basic cGF2YW5rYXNoeWFwMTIxMEBvdXRsb29rLmNvbTpBVEFUVDN4RmZHRjBQUlItTVpTS04tcFBiOHdYS1c5d0pmRlh6U1U5WGNnUzRkNjRzNXEzRElLT2d5SXlXZmlZODZrTGpseDdCQ2w2cUVSYUw0Y1lhR2sweEhMWllJVzB2WWRJZU1kSEVIZUdzRGdMbFJDeUFNUUYxX0R5LUNRMDFmSVBWcG94MnVwVm5nbUI2Q191bHl0cFJJZWowR04ybTZKVndmU1NiNHNpWjZfWFdtYkNpVzA9ODQwQkY5Qjg=")
		.header("X-Atlassian-Token","no-check")
		// any attachments are treated as multipart in rest assured
		// we need to send it as key value pair
		// key is file and file should be sent as an File Object
		.multiPart("file",new File("C:\\Users\\Lenovo\\eclipse-workspace\\Rest_Assured_API\\addPlace.json"))
		.when()
		 .post("rest/api3/issue/{key}/attachments")
		//("rest/api3/issue/"+ issueID + "/attachments"); or use a path parameter and use {} in post url
		 .then().log().all().assertThat().statusCode(200).extract().asString();
	}
		

}
