package com.ecommerce.main.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.main.dao.CartDetails;
import com.ecommerce.main.dao.UserDetails;
import com.ecommerce.main.repository.UserDetailsRepository;
import com.ecommerce.main.service.UserDetailsService;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	
	public String addUserDetails(UserDetails user) throws Exception {
		CartDetails cart=new CartDetails();
	
		int i=userDetailsRepository.CountRow();
		cart.setUserId(i+1);
		user.setCart(cart);
		user.setOrderId(null);
		userDetailsRepository.save(user);
		return "Sucessfully Added";
	}
	
	
	public String deleteUserDetails(long id){
		if(userDetailsRepository.existsById(id)) {
			userDetailsRepository.deleteById(id);
			return "SucessFully Deleted";
		}
		return "Illegal Operation";
	}
	
	public Optional<UserDetails> getUserDetails(long id) {
	//	System.out.println("hye"+id);
		return userDetailsRepository.findById(id);
		
	}
	public String updateUserDetails(UserDetails user,long id) {
		if(userDetailsRepository.existsById(id)) {
		userDetailsRepository.save(user);
		return "SucessFully Updated";
		}
		return "Illegal Operation";
	}
	
	public List<UserDetails> getAllUserDetails(){
		
		List <UserDetails> userList = new ArrayList<>();
		userDetailsRepository.findAll().
		forEach(userList::add);
					
		if(userList== null) 
			return  null;
		return userList;
	}


	
	public UserDetails userAuthentication(String emailId,String password) {
		
		UserDetails user=userDetailsRepository.findByemailIdAndPassword(emailId,password);
		
		if(user!=null)
		return user;
		
		return null;
	}
	
}
