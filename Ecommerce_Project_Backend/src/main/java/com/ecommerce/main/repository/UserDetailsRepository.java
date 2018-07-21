package com.ecommerce.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.main.dao.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

	public UserDetails findByemailIdAndPassword(String emailId,String password);
	public UserDetails findByemailId(String emailId);
	
	
	public static final String COUNT_ROWS = "SELECT count(*)  FROM user_details";
	@Query(value = COUNT_ROWS, nativeQuery = true)
	public int CountRow();
}
