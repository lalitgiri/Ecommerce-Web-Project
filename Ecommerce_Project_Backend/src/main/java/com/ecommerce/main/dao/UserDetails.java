package com.ecommerce.main.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity

public class UserDetails implements Serializable {
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "UserID") //uniqueKey
	private CartDetails cart;
	@Id
	private long  phoneNumber;	//primaryKey
	private String name;
	private String emailId; 
	
	
	private String password;

	
	@OneToMany(mappedBy="userId")
	@Transient
	private Collection<OrderDetails> orderId;
	
	public Collection<OrderDetails> getOrderId() {
		return orderId;
	}
	public void setOrderId(Collection<OrderDetails> orderId) {
		this.orderId = orderId;
	}
	public CartDetails getCart() {
		return cart;
	}
	public void setCart(CartDetails cart) {
		this.cart = cart;
	}
	
		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
