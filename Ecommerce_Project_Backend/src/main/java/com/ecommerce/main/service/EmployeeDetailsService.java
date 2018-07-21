package com.ecommerce.main.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.main.dao.EmployeeDetails;

public interface EmployeeDetailsService {

	public String addEmployeeDetails(EmployeeDetails employee) throws Exception ;
	public String deleteEmployeeDetails(int id);
	public Optional<EmployeeDetails> getEmployeeDetails(int id) ;
	public String updateEmployeeDetails(EmployeeDetails employee,int id) ;
	public List<EmployeeDetails> getAllEmployeeDetails();
	public EmployeeDetails userAuthentication(String userName, String password);
}
