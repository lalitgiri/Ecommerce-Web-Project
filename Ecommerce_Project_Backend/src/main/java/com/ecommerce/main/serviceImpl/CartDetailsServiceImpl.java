package com.ecommerce.main.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.main.cartHandler.CartOrderHandlerSupportClass;
import com.ecommerce.main.cartHandler.ProductQuantity;
import com.ecommerce.main.dao.CartDetails;
import com.ecommerce.main.dao.ProductTable;
import com.ecommerce.main.repository.CartDetailsRepository;
import com.ecommerce.main.service.CartDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CartDetailsServiceImpl implements CartDetailsService {

	@Autowired
	private CartDetailsRepository cartDetailsRepository;

	CartDetails cart = new CartDetails();
	List<ProductTable> productList = new ArrayList<>();

	public String getCartDetails(int id) {
		String jsonResult = null;
		CartOrderHandlerSupportClass cartOrderHandlerSupportClass = new CartOrderHandlerSupportClass();
		List<ProductQuantity> productQuantity = new ArrayList<ProductQuantity>();

		if (cartDetailsRepository.findById(id).get() != null) {

			CartDetails cartDetails = cartDetailsRepository.findById(id).get();

			List<ProductTable> list = cartDetails.getProductId();
			Set<ProductTable> uniqueProducts = new HashSet<>();
			list.forEach(p -> uniqueProducts.add(p));
			List<ProductTable> uniqueIds = uniqueProducts.stream().collect(Collectors.toList());
			// int count = 0;
			for (ProductTable i : uniqueIds) {
				int count = 0;
				for (ProductTable p : list) {
					if (p.equals(i)) {
						count++;
					}
				}
				productQuantity.add(new ProductQuantity(count, i));

			}

			// productQuantity.forEach(System.out::print);

			cartOrderHandlerSupportClass.setProductQuantity(productQuantity);

			cartOrderHandlerSupportClass.setDeliveryAddress(cartDetails.getDeliveryAddress());

			cartOrderHandlerSupportClass.setUserId(cartDetails.getUserId());

			ObjectMapper mapper = new ObjectMapper();
			try {
				jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cartOrderHandlerSupportClass);

			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			// Set<Entry<ProductTable,Integer>> ssss = data.entrySet();
			// ssss.forEach(System.out::println); return cartDetailsRepository.findById(id);

			return jsonResult;

		}
		return "hello";
	}

	public String updateCartDetails(ProductTable product, int id, int quantity) {
		boolean flag=false;
		
		CartDetails oldCart= cartDetailsRepository.findById(id).get();
		
		for (int i = 0; i < quantity; i++) {
			if (product != null) {
				cart.setUserId(id);
				
				if (addItemToCart(product, id))
					cart.setProductId(productList);
				cart.setDeliveryAddress(oldCart.getDeliveryAddress());
				if (cartDetailsRepository.save(cart) != null)
					flag=true;
				else
					{
						flag=false;
						break;
					}
			}
		}
		
		if(flag==true)
			return "SucessFully Updated";

		return "error";
	}

	public boolean addItemToCart(ProductTable product, int id) {
		productList = new ArrayList<>();
		Optional<CartDetails> cart2 = cartDetailsRepository.findById(id);
		if (cart2.isPresent()) {
			productList = cart2.get().getProductId();
			productList.add(product);
			return true;
		}

		return false;
	}

	public List<CartDetails> getAllCartDetails() {

		List<CartDetails> cartList = new ArrayList<>();
		cartDetailsRepository.findAll().forEach(cartList::add);

		return cartList;
	}

	public String removeItemFromCart(ProductTable product, int id) {

		Optional<CartDetails> cart2 = cartDetailsRepository.findById(id);

		if (cart2.isPresent()) {
			productList = cart2.get().getProductId();

			if (removeItemLocal(product)) {
				cart2.get().setProductId(productList);
				cartDetailsRepository.save(cart2.get());
				return "itemRemoved";
			}
			return "item not removed";

		}
		return "Item Not Avaliable In Cart";
	}

	public boolean removeItemLocal(ProductTable product) {

		// System.out.println("List size before removed products: "+productList.size());
		List<ProductTable> list = new ArrayList<>();
		productList.forEach(u -> {
			if (u.getProductId() != product.getProductId()) {
				list.add(u);
			}

		});
		if (list != null) {
			productList = list;
			return true;
		}
		return false;

	}
}
