package com.ecommerce.main.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.main.dao.ProductTable;
import com.ecommerce.main.service.ProductTableService;

@RestController
public class ProductTableServiceController {
	
	@Autowired
	private ProductTableService productTableService;
	
	@RequestMapping(method=RequestMethod.POST,value="/addproduct/{employeeId}")
	public String addProduct(@RequestBody ProductTable product,@PathVariable int employeeId) throws Exception {
		
			return productTableService.addProduct(product, employeeId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/deleteproduct")
	public String deleteProduct(@RequestBody ProductTable id[]){
		return productTableService.deleteProduct(id);
	}
	
	@RequestMapping("/getproduct/{id}")
	public Optional<ProductTable> getProductDetails(@PathVariable int id) {
		
		return productTableService.getProductDetails(id);
	}

	@RequestMapping(method=RequestMethod.POST,value="/updateproduct/{id}")
	public String updateProductDetails(@RequestBody ProductTable product,@PathVariable long id) {
		
		return productTableService.updateProductDetails(product, id);
	}
	
	@RequestMapping("/getallproduct")
	public List<ProductTable> getAllProducts(){
		
		return productTableService.getAllProducts();
	}
	
	@RequestMapping("/getallproductCategory")
	public List<String> getAllProductCategory(){
		return productTableService.getAllProductCategory();
	}
	
	@RequestMapping("/getproductbycategory/{category}")
	public List<ProductTable> getProductByCategory(@PathVariable String category)
	{
		return productTableService.getProductByCategory(category);
	}
	
	@RequestMapping(value="/searchProduct/{category}/{name}",method=RequestMethod.GET)
	public Iterable<ProductTable> getByProductNameAndCategory(@PathVariable String category,@PathVariable String name){
		return productTableService.getByProductNameAndCategory(category.toUpperCase(), name.toUpperCase());
	}
}