package com.ecommerce.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.main.dao.CartDetails;
import com.ecommerce.main.dao.ProductTable;
import com.ecommerce.main.service.CartDetailsService;


@RestController
public class CartDetailsServiceController {

	@Autowired
	private CartDetailsService cartDetailsService;
	
	@RequestMapping("token/getcart/{id}")
	public String getCartDetails(@PathVariable int id){

		return cartDetailsService.getCartDetails(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="token/updatecart/{id}/{quantity}")
	public String updateCartDetails(@PathVariable int id,@PathVariable int  quantity,@RequestBody ProductTable product) {
	 String str=cartDetailsService.updateCartDetails(product, id,quantity);
	 return str;
	}
	
	@RequestMapping("token/getallcartsdetails")
	public List<CartDetails> getAllCartDetails(){
		return cartDetailsService.getAllCartDetails();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="token/removefromcart/{id}")
	public String removeItemFromCart(@RequestBody ProductTable product,@PathVariable int id) {
		return cartDetailsService.removeItemFromCart(product, id);
	}	
}
