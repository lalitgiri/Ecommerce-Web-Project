package com.ecommerce.main.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.main.dao.UserDetails;

public interface UserDetailsService {

	
	public String addUserDetails(UserDetails user) throws Exception ;
	public String deleteUserDetails(long id);
	public Optional<UserDetails> getUserDetails(long id);
	public String updateUserDetails(UserDetails user,long id);
	public List<UserDetails> getAllUserDetails();
	public UserDetails userAuthentication(String emailId,String password);
}
