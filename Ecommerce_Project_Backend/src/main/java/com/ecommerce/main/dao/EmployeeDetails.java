package com.ecommerce.main.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmployeeDetails implements Serializable {
	
	@Id
	private int employeeId;		//primary Key
	private String employeeName;
	private String employeeRole;
	private long contactNumber;
	private String address;
	private Date dob;
	private String password;
	private String empImageUrl;
	private boolean status;
	private Date joiningDate,resigningDate;
	
	@JsonIgnore
	@OneToMany(mappedBy="employeeId",fetch=FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private Collection <ProductTable> productId;
	
	public Collection<ProductTable> getProductId() {
		return productId;
	}
	public void setProductId(Collection<ProductTable> productId) {
		this.productId = productId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Date getResigningDate() {
		return resigningDate;
	}
	public void setResigningDate(Date resigningDate) {
		this.resigningDate = resigningDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getEmpImageUrl() {
		return empImageUrl;
	}
	public void setEmpImageUrl(String empImageUrl) {
		this.empImageUrl = empImageUrl;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}

