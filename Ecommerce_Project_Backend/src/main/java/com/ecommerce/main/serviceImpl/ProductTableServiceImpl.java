package com.ecommerce.main.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.main.dao.ProductTable;
import com.ecommerce.main.repository.EmployeeDetailRepository;
import com.ecommerce.main.repository.ProductTableRepository;
import com.ecommerce.main.service.ProductTableService;

@Service
public class ProductTableServiceImpl implements ProductTableService {

	@Autowired
	private ProductTableRepository productTableRepository;
	
	@Autowired
	private EmployeeDetailRepository employeeDetailRepository;
	public String addProduct(ProductTable product,int employeeId) throws Exception {
		int i=productTableRepository.CountRow();
		product.setProductId(i+1);	
		product.setEmployeeId(employeeDetailRepository.findById(employeeId).get());
		//System.out.println("i="+i+" i+1="+(i+1));
		productTableRepository.save(product);
		return "Sucessfully Added";
	}
	
	
	public String deleteProduct(ProductTable id[]){
		
		for(ProductTable i : id) {
			try{if(productTableRepository.existsById(i.getProductId()))
				{
					i.setStatus(false);
					productTableRepository.save(i);
				
				}
			}catch(Exception e) {
				return "Error Deleting item with id=" + i;
			}
		}		
		return "Sucessfully Deleted";
	
	}
	
	public Optional<ProductTable> getProductDetails(int id) {
		
		return productTableRepository.findById(id);
	}
	
	public String updateProductDetails(ProductTable product,long id) {
		
		productTableRepository.save(product);
		return "SucessFully Updated";
	}
	
	public List<ProductTable> getAllProducts(){
		
		List <ProductTable> productList = new ArrayList<>();
		productTableRepository.findByStatus().
		forEach(productList::add);
		if(productList==null)
			return null;
		return productList;
	}
	
	public List<String> getAllProductCategory(){
	  /*List<ProductTable> productList = (List<ProductTable>) productTableRepository.findAll();
	  List<String> categories = new ArrayList<>();
	  productList.forEach(p -> {
		  	if(!categories.contains(p.getProductCategory())) {
		  		categories.add(p.getProductCategory());
		  	}
	  });
	  return categories;*/
		return productTableRepository.findCategory();
	}


	@Override
	public List<ProductTable> getProductByCategory(String category) {
		return productTableRepository.findByproductCategory(category);
	}
	
	public Iterable<ProductTable> getByProductNameAndCategory(String category,String name){
		return productTableRepository.findByProductNameWithCategory(category,name);
		
	}
	
}
