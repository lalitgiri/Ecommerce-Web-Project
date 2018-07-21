package com.ecommerce.main.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.main.cartHandler.CartOrderHandlerSupportClass;
import com.ecommerce.main.dao.OrderDetails;
import com.ecommerce.main.dao.UserDetails;
import com.ecommerce.main.repository.OrderDetailsRepository;
import com.ecommerce.main.repository.UserDetailsRepository;
import com.ecommerce.main.service.OrderDetailsService;

@Service

public class OrderDetailsServiceImpl implements OrderDetailsService {
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Override

	public String addOrder(OrderDetails order) {
		long c = orderDetailsRepository.count()+1;
		order.setOrderId(c);
		order.setStatus(true);
		orderDetailsRepository.save(order);
		return "Order Placed Sucessfully";
	}

	@Override
	public List<OrderDetails> getAllOrder() {
			List <OrderDetails> orderList = new ArrayList<OrderDetails>();
			orderDetailsRepository.findAll().forEach(orderList::add);
			if(orderList==null)
				return null;
			return orderList;
	}

	@Override
	public String deleteOrder(int id) {
		orderDetailsRepository.deleteById(id);
		return "Sucessfully Deleted";
	}

	@Override
	public Optional<OrderDetails> getOrderById(int id) {
		return orderDetailsRepository.findById(id);
		
	}

	@Override
	public String updateDeliveryAddress(OrderDetails order, int id) {
		if(orderDetailsRepository.existsById(id))
		{
			orderDetailsRepository.save(order);
			return "SucessFully Updated"; 
		}
		
		return "Illegal Modification";
	}

	@Override
	public String addCartOrder(CartOrderHandlerSupportClass	order) {

		order.getProductQuantity().forEach(u -> {
			OrderDetails orderDetails = new OrderDetails();
		//	System.out.println("User Id:" +order.getUserId());
			orderDetails.setUserId(userDetailsRepository.findById((long)order.getUserId()).get());
			orderDetails.setAddress(order.getDeliveryAddress());
			orderDetails.setStatus(true);
			
			long c = orderDetailsRepository.count()+1;
			orderDetails.setOrderId(c);
			
			orderDetails.setQuantity(u.getQuantity());
			orderDetails.setItemDetail(u.getProductTable());
			
			orderDetailsRepository.save(orderDetails);
		});
		return "Sucessfully Order Placed !!!";
	}
	
	

}
