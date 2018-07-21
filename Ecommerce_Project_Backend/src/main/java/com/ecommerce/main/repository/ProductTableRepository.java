package com.ecommerce.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.main.dao.ProductTable;

public interface ProductTableRepository extends CrudRepository<ProductTable,Integer>{

	public static final String FIND_CATEGORY = "SELECT DISTINCT product_category FROM product_table";
	@Query(value = FIND_CATEGORY, nativeQuery = true)
	public List<String> findCategory();	
	
	public List<ProductTable> findByproductCategory(String category);
	
	public static final String COUNT_ROWS = "SELECT count(product_id)  FROM product_table";
	@Query(value = COUNT_ROWS, nativeQuery = true)
	public int CountRow();
	
	
	public static final String FIND_BY_STATUS = "SELECT * FROM product_table where status=true";
	@Query(value =FIND_BY_STATUS, nativeQuery = true)
	public Iterable<ProductTable> findByStatus();
	/*
	 @Query("select u from User u where u.firstname like %?1")
	  List<User> findByFirstnameEndsWith(String firstname);*/
	
	 @Query(value="select * from Product_Table u where UPPER(u.product_category) = :category AND UPPER(u.product_name) LIKE CONCAT('%',:productName,'%')", nativeQuery = true)
	  public Iterable<ProductTable> findByProductNameWithCategory(@Param("category") String category, @Param("productName") String productName);
}
