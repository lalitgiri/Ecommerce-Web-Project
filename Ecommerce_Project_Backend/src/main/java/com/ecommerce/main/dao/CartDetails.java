
package com.ecommerce.main.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class CartDetails implements Serializable {
	
	@Id
	@Column(name = "UserID")
	private int userId;		//primaryKey
	@ManyToMany
	private List<ProductTable> productId =new ArrayList<>();	//foreginKey
	
	private String deliveryAddress;
	
	

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	
	public List<ProductTable> getProductId() {
		return productId;
	}
	public void setProductId(List<ProductTable> productId) {
		this.productId = productId;
	}
	
}
