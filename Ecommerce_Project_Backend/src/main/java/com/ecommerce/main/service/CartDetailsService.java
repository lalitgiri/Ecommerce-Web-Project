package com.ecommerce.main.service;

import java.util.List;

import com.ecommerce.main.dao.CartDetails;
import com.ecommerce.main.dao.ProductTable;


public interface CartDetailsService {
	

	public String getCartDetails(int id);
	
	public String updateCartDetails(ProductTable product,int id,int quantity) ;
	
	public List<CartDetails> getAllCartDetails();
	
	public String removeItemFromCart(ProductTable product,int id);
}
