package com.ecommerce.main.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.main.dao.ProductTable;

public interface ProductTableService {


	public String addProduct(ProductTable product,int employeeId) throws Exception ;
	
	public String deleteProduct(ProductTable id[]);
	public Optional<ProductTable> getProductDetails(int id);
	public String updateProductDetails(ProductTable product,long id) ;
	public List<ProductTable> getAllProducts();
	public List<String> getAllProductCategory();

	public List<ProductTable> getProductByCategory(String category);
	
	public Iterable<ProductTable> getByProductNameAndCategory(String category,String name);
}
