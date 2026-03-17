package PojoClasses;

public class OrderDetailsPayload {
	
	private String country;
	private String productOrderedId;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProductOrderId() {
		return productOrderedId;
	}
	public void setProductOrderId(String productOrderId) {
		this.productOrderedId = productOrderId;
	} 

}
