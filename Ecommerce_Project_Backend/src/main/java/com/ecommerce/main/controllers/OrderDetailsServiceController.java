package com.ecommerce.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.main.cartHandler.CartOrderHandlerSupportClass;
import com.ecommerce.main.dao.OrderDetails;
import com.ecommerce.main.service.OrderDetailsService;


@RestController
public class OrderDetailsServiceController {

	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@RequestMapping(method=RequestMethod.POST,value="/add_cart_order")
	public String addCartOrder(@RequestBody CartOrderHandlerSupportClass order) {
		return orderDetailsService.addCartOrder(order);
	}
	@RequestMapping(method=RequestMethod.POST,value="/addorder")
	public String addOrder(@RequestBody OrderDetails order) {
		return orderDetailsService.addOrder(order);
	}
	@RequestMapping("/getallorder")
	public List<OrderDetails> getAllOrder(){
		return orderDetailsService.getAllOrder();
	}
	@RequestMapping("/deleteorder/{id}")
	public String deleteOrder(@PathVariable int id) {
		return orderDetailsService.deleteOrder(id);
	}
	
	@RequestMapping("/getorderbyid/{id}")
	public Optional<OrderDetails> getOrderById(@PathVariable int id){
		return orderDetailsService.getOrderById(id);
	}
	@RequestMapping(method=RequestMethod.POST,value="/updateAddress/{id}")
	public String updateDeliveryAddress(@RequestBody OrderDetails order,@PathVariable int id) {
		return orderDetailsService.updateDeliveryAddress(order, id);
	}
}
