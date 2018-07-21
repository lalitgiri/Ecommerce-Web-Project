package com.ecommerce.main.cartHandler;

import java.util.List;

public class CartOrderHandlerSupportClass {
	
	private long userId;
	private String deliveryAddress;
	
	private List<ProductQuantity> productQuantity;
	
	public List<ProductQuantity> getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(List<ProductQuantity> productQuantity) {
		this.productQuantity = productQuantity;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	

}
