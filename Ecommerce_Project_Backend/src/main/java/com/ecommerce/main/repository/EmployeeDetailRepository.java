package com.ecommerce.main.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.main.dao.EmployeeDetails;

public interface EmployeeDetailRepository extends CrudRepository<EmployeeDetails,Integer>{

	EmployeeDetails findByemployeeNameAndPassword(String UserName, String password);
/*	@Modifying(clearAutomatically = true)
    @Query("UPDATE EmployeeDetails c SET c.status = :status WHERE c.id = :employeeId")
    int updateAddress(@Param("employeeId") int companyId, @Param("status") String address);
    
		 @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    public List<Person> find(@Param("lastName") String lastName);
    
    @Query("SELECT t.title FROM Todo t where t.id = :id") 
    String findTitleById(@Param("id") Long id);
*
*/
	public static final String COUNT_ROWS = "SELECT count(*)  FROM employee_details";
	@Query(value = COUNT_ROWS, nativeQuery = true)
	public int CountRow();
	
	public static final String CHANGE_STATUS = "update employee_details set p.status='False' where p.id = :id";
	@Query(value =CHANGE_STATUS, nativeQuery = true)
	public int ChangeStatus(@Param("id") int id);
}
