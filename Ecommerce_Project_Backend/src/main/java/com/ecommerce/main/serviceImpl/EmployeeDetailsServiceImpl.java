package com.ecommerce.main.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.main.dao.EmployeeDetails;
import com.ecommerce.main.repository.EmployeeDetailRepository;
import com.ecommerce.main.service.EmployeeDetailsService;

@Service
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

	@Autowired
	private EmployeeDetailRepository employeeDetailsReposiotory;

	public String addEmployeeDetails(EmployeeDetails employee) throws Exception {
		
		int i = employeeDetailsReposiotory.CountRow();
		employee.setEmployeeId(i);
		employeeDetailsReposiotory.save(employee);
		return "Sucessfully Added";
	}

	public String deleteEmployeeDetails(int id) {
		if (employeeDetailsReposiotory.existsById(id)) {
			//employeeDetailsReposiotory.deleteById(id);
			employeeDetailsReposiotory.ChangeStatus(id);
			return "SucessFully Deleted";
		}
		return "Employee NotFound";
	}

	public Optional<EmployeeDetails> getEmployeeDetails(int id) {

		return employeeDetailsReposiotory.findById(id);
	}

	public String updateEmployeeDetails(EmployeeDetails employee, int id) {

		if (employeeDetailsReposiotory.existsById(id)) {
			employeeDetailsReposiotory.save(employee);
			return "SucessFully Updated";
		}
		return "Illegal Modification";
	}

	public List<EmployeeDetails> getAllEmployeeDetails() {

		List<EmployeeDetails> employeeList = new ArrayList<>();
		employeeDetailsReposiotory.findAll().forEach(employeeList::add);

		if (employeeList == null)
			return null;

		return employeeList;
	}

	@Override
	public EmployeeDetails userAuthentication(String userName, String password) {
		EmployeeDetails user = employeeDetailsReposiotory.findByemployeeNameAndPassword(userName, password);

		if (user != null)
			return user;

		return null;
	}

}
