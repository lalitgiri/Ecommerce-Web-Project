package com.ecommerce.main.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetails implements Serializable {

	@Id
	private long orderId;  //primaryKey
	
	private String  address;
	private ProductTable itemDetail;
	private int quantity ;
	
	private boolean status;
	@ManyToOne//(fetch=FetchType.EAGER)
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private UserDetails userId;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public UserDetails getUserId() {
		return userId;
	}
	public void setUserId(UserDetails userId) {
		this.userId = userId;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ProductTable getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(ProductTable itemDetail) {
		this.itemDetail = itemDetail;
	}
	
	
}
