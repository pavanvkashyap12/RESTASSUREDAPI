import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicPayload {
	
	@Test(dataProvider = "BooksData")
	public void addBook(String name, String isbn, String aisle, String author) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		// ADD BOOK aisle and isbn number is dynamic
		String response = given().log().all().header("Content-Type","application/json").body(Payload.addBook(name,isbn,aisle,author))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println("ID----->"+id);
		
	}
	
	// DataProvider
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		// array is a collection of elements
		// Multidimensional array is collection of arrays
		return new Object[][] {{"learn playwright","123","a","John Dean"},{"learn playwright1","1234","ab","John Dean1"},{"learn playwright2","12345","abc","John Dean2"}};
	}
	
	// For every execution one array will be sent to addBook() 
	// and in test when you use dataProvider add method parameters to test, same number of parameters should be there in test method and data provider
	// Now this test will run 3 times with these 3 arrays
	// Now we can see 3 tests passed
	
	

}
