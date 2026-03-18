package e2eEcommerceAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import PojoClasses.AddProductResponse;
import PojoClasses.LoginRequest;
import PojoClasses.LoginResponse;
import PojoClasses.OrderDetailsPayload;
import PojoClasses.PlaceOrderPayload;

public class EcommerceAPITest {

	public static void main(String[] args) {
		
		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
		ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		// Login and get token
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("anshika@gmail.com");
		loginRequest.setUserPassword("Iamking@000");
		
		RequestSpecification loginReq = given().log().all().spec(request).body(loginRequest);
		
		// this is the call 
		LoginResponse  loginResponse =loginReq.when().post("/api/ecom/auth/login").then().log().all().spec(response).extract().response().as(LoginResponse.class);
		String authToken = loginResponse.getToken();
		String userId = loginResponse.getUserId();
		System.out.println("------------------------------------");
		System.out.println("TOKEN" + loginResponse.getToken()); 
		System.out.println("USERID" + loginResponse.getUserId());
		System.out.println("------------------------------------");

		// Create a new product
		RequestSpecification requestAddProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("Authorization", authToken).build();
		
		RequestSpecification addProductRequest = given().log().all().spec(requestAddProduct).param("productName","Mice2.0").param("productAddedBy", userId).param("productCategory", "utility")
		.param("productSubCategory", "utlity2.0").param("productPrice", "1150").formParam("productDescription", "Lenovo").param("productFor", "all")
		.multiPart("productImage", new File("C:\\Users\\Lenovo\\eclipse-workspace\\Rest_Assured_API\\OAuthBasics.png"));
		// when you want to send a file you need to use multipart instead of param
		
		// this is the call 
		AddProductResponse addProductResponse =addProductRequest.when().post("/api/ecom/product/add-product").then().log().all().extract().response().as(AddProductResponse.class);
		String addProductmessage = addProductResponse.getMessage();
		String productId = addProductResponse.getProductId();
		Assert.assertEquals(addProductmessage,"Product Added Successfully");
		
		// Place an order
		RequestSpecification requestPalceOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("Authorization", authToken).setContentType(ContentType.JSON).build();
		
		List<OrderDetailsPayload> oDPL = new ArrayList<OrderDetailsPayload>();
		OrderDetailsPayload oDP = new OrderDetailsPayload();
		oDP.setCountry("India");
		oDP.setProductOrderId(productId);
		System.out.println("GET PRODUCT ID ----> " + oDP.getProductOrderId());
		oDPL.add(oDP);
		
		PlaceOrderPayload poP = new PlaceOrderPayload();
		poP.setOrders(oDPL);
		
		RequestSpecification placeOrderRequest = given().log().all().spec(requestPalceOrder).body(poP);
		// this is the call 
		String placeOrderResponse = placeOrderRequest.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(placeOrderResponse);
		System.out.println("--------------PLACE ORDER-------------------");
		System.out.println("placeOrderResponse---->" + placeOrderResponse);
		System.out.println("Message" + js.getString("message"));
		System.out.println("--------------PLACE ORDER-------------------");

		
		
		// Delete Product
		// here we are send a parameter in url which is called as path parameter
		RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addHeader("Authorization", authToken).setContentType(ContentType.JSON).build();
		RequestSpecification deleteProdReq = given().log().all().spec(deleteProdBaseReq).pathParam("productId", productId);
		// this is the call
		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
		// path parameter can be sent in url by using {"exactKey"}
		JsonPath js1 = new JsonPath(deleteProductResponse);
		Assert.assertEquals("Product Deleted Successfully", js1.get("message"));
		
		// sometimes API needs SSL certification to by pass that we can use given().relaxedHTTPSValidation()
		
		
		

		
		
		
	}
}
