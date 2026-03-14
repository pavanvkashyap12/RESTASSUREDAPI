import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;


public class ComplexJson {
	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(Payload.complexJson());
		
		// 1. Print No of courses returned by API 
		// courses is an array so we should check size of Array
		int numberOfCourses = js.getInt("courses.size()");
		System.out.println("Number Of Courses = " + numberOfCourses);
		
		// 2.Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount = " + purchaseAmount);
		
		// 3. Print Title of the first course
		String firstCourse = js.getString("courses[0].title");
		
		// 4. Print All course titles and their respective Prices
		for (int i=0;i<numberOfCourses;i++) {
			String course = js.getString("courses[" + i + "].title");
			int price = js.getInt("courses[" + i + "].price");
			System.out.println("Course is " + course + " and price is " + price);
		}
		
		// 5. Print no of copies sold by RPA Course
		for (int i=0;i<numberOfCourses;i++) {
			if(js.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
				int copies = js.getInt("courses[" + i + "].copies");
				System.out.println("Number of copies sold by RPA is " + copies);
				break;
			}
		}
		
		// 6. Verify if Sum of all Course prices matches with Purchase Amount
		int sumOfAllCourses = 0;
		for (int i=0;i<numberOfCourses;i++) {
			int price = js.getInt("courses[" + i + "].price"); // price
			int copies = js.getInt("courses[" + i + "].copies"); // copies
			int sum = price * copies ; // sum = price * copies
			sumOfAllCourses = sumOfAllCourses + sum ;
		}
		System.out.println("Sum Of All Courses " + sumOfAllCourses);
		Assert.assertEquals(sumOfAllCourses,purchaseAmount,"Purchase Amount does not match Sum Of All Courses");	
	}
	

}
