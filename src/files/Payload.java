package files;

public class Payload {
	
	public static String addPlace() {
		String payload = "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "    \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}";
		return payload ;
	}
	
	public static String putPlace (String place_id, String address) {
		String payload = "{\r\n"
				+ "\"place_id\":\"" + place_id + "\",\r\n"
				+ "\"address\":\"" + address +"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
		return payload;
	}
	
	public static String complexJson() {
		 String payload = "{\r\n"
		 		+ "  \"dashboard\": {\r\n"
		 		+ "    \"purchaseAmount\": 910,\r\n"
		 		+ "    \"website\": \"rahulshettyacademy.com\"\r\n"
		 		+ "  },\r\n"
		 		+ "  \"courses\": [\r\n"
		 		+ "    {\r\n"
		 		+ "      \"title\": \"Selenium Python\",\r\n"
		 		+ "      \"price\": 50,\r\n"
		 		+ "      \"copies\": 6\r\n"
		 		+ "    },\r\n"
		 		+ "    {\r\n"
		 		+ "      \"title\": \"Cypress\",\r\n"
		 		+ "      \"price\": 40,\r\n"
		 		+ "      \"copies\": 4\r\n"
		 		+ "    },\r\n"
		 		+ "    {\r\n"
		 		+ "      \"title\": \"RPA\",\r\n"
		 		+ "      \"price\": 45,\r\n"
		 		+ "      \"copies\": 10\r\n"
		 		+ "    }\r\n"
		 		+ "  ]\r\n"
		 		+ "}";
		 
		return payload;
	}
	
	public static String addBook(String name, String isbn, String aisle, String author) {
		String payload = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"" + name + "\",\r\n"
				+ "\"isbn\":\"" + isbn + "\",\r\n"
				+ "\"aisle\":\"" + aisle + "\",\r\n"
				+ "\"author\":\"" + author + "\"\r\n"
				+ "}";
		return payload ;
	}

}
