package org.makerminds.javaweb.repositories;

import java.util.Optional;

import org.makerminds.javaweb.entity.Department;
import org.makerminds.javaweb.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository  extends JpaRepository<Employee,Long>{
	
	public Optional<Employee> findById(Long id);

}
