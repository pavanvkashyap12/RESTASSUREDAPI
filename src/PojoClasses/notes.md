# Serialization And De-serialization Of Requests/Responses with POJO 

Serialization in Rest Assured context is a process of converting Java Object into Request body (Payload).
Rest Assured also supports de-serilization by converting Response body back to Java Object.

Advantages:
- Easy to parse and extract response (Json/XML) values if they are wrapped as Java object.
- User friendly methods can be created which makes code more readable.

Design Approach:
- Java object is constructed with the support of POJO classes.
- POJO classes are created based on the request/response payload.

What Additional libraries are required ?
- For JSON you need to have either Jackson, Jackson2, Gson or Johzon in the classpath and for XML you need JAXB

```java 
Ex: 

// POJO Classes -> Plain Object Java Classes
public class Message {

	private String message;
	private String greet;
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getGreet(){
		return greet;
	}
	
	public void setGreet(String greet){
		this.greet = greet;
	}
}

This POJO class is equal to { "message"":"","greet":"" } 

// Create Java Object
Message message = new Message();
message.setMessage("Hello");
meesage.setGreet("Hi");
This is now equal to { "message"":"Hello","greet":"Hi" }

// Rest Assured
Message message = new Message();
message.setMessage("My message");
message.setGreet("My Greet");
given().body(message).when().post("/message")// here in body we are sending message object

// Run time request creation will be like this
{ message:"Hello", "greet":"Hi" }

So this is serialization
```

``` java
DESIRIALIZATION 

Assume this is the response { "message"":"Hello","greet":"Hi" }

// we use same POJO class for both serialization and de-sirialization
// Rest assured automatically sets message and greet to respective methods with setters
// Now just use getters to retrieve it 

Message message = new Message();
message.getMessage("My message");
message.getGreet("My Greet");
```

- jackson databind -> in maven and add it to POM.xml ->  com.fasterxml.jackson.core -> this gives capability of serialization and de-sirialization



