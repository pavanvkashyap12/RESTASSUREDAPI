package PojoClasses;

import java.util.List;

public class PlaceOrderPayload {
	
	private List<OrderDetailsPayload> orders;

	public List<OrderDetailsPayload> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetailsPayload> orders) {
		this.orders = orders;
	}
	

}
