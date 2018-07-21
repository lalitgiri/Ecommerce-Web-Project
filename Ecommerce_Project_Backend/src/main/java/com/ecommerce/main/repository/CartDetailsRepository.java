package com.ecommerce.main.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.main.dao.CartDetails;

public interface CartDetailsRepository extends CrudRepository<CartDetails,Integer> {

}
