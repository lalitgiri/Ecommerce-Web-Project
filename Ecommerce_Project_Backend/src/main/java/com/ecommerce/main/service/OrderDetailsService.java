package com.ecommerce.main.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.main.cartHandler.CartOrderHandlerSupportClass;
import com.ecommerce.main.dao.OrderDetails;

public interface OrderDetailsService {

	public String addOrder(OrderDetails order);
	public List<OrderDetails> getAllOrder();
	public String deleteOrder(int id);
	public Optional<OrderDetails> getOrderById(int id);
	public String updateDeliveryAddress(OrderDetails order,int id);//this is only for to update address...
	public String addCartOrder(CartOrderHandlerSupportClass order);
}

