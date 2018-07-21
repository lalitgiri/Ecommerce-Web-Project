package com.ecommerce.main.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.main.dao.OrderDetails;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails,Integer>{

	/* @Modifying(clearAutomatically = true)
	    @Query("UPDATE OrdersDetails c SET c.address = :address WHERE c.id = :orderId")
	    int updateAddress(@Param("orderId") int companyId, @Param("address") String address);
	 */
	
	public static final String COUNT_ROWS = "SELECT count(product_id)  FROM Order_Details";
	@Query(value = COUNT_ROWS, nativeQuery = true)
	public int CountRow();
}
