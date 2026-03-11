package org.makerminds.javaweb.service;

import java.util.List;

import org.makerminds.javaweb.entity.Department;
import org.makerminds.javaweb.entity.Employee;
import org.makerminds.javaweb.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import org.makerminds.javaweb.repositories.DepartmentRepository;
import org.makerminds.javaweb.repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentRepository;
	private final DepartmentService departmentService;
	
	  
	public Employee createOrUpdateEmployee(Employee employee, Long departmentId) {
	    Department dept = departmentService.findById(departmentId);
	    if (dept == null) {
	        throw new RuntimeException("Department not found");
	    }
	    employee.setDepartment(dept);
	    return employeeRepository.save(employee);
	}

	public Employee findMyId(Long id) {
		return employeeRepository.findById(id).orElse(null);
	}
	public List<Employee> getEmployeeList(Long departmentId){
		return departmentRepository.findById(departmentId).get().getEmployeeList();
	}
	
	@Transactional
	public ResponseEntity<?> deleteEmployeeById(Long departmentId, Long id) {

	    Employee employee = findMyId(id);

	    if (employee != null) {

	        if (employee.getDepartment().getId().equals(departmentId)) {

	            Department department = employee.getDepartment();
	            department.getEmployeeList().remove(employee);

	            employeeRepository.delete(employee);

	            return ResponseEntity.ok("Employee with id: " + id + " has been deleted!");

	        } else {
	            throw new EmployeeNotFoundException("Employee not found in this department!");
	        }

	    } else {
	        throw new EmployeeNotFoundException("Employee not found!");
	    }
	}
	public Employee getEmployee(Long departmentId, Long id) {
	    Employee employee = findMyId(id); 
	    if(employee == null || employee.getDepartment().getId() != departmentId) {
	        throw new EmployeeNotFoundException("Employee not found.");
	    }
	    return employee;
	}

	

}
