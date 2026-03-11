package org.makerminds.javaweb.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.javaweb.entity.Employee;
import org.makerminds.javaweb.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin()
public class EmployeeControllers {
	
	private final EmployeeService employeeService;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> createEmployee(
	        @PathVariable Long id,
	        @RequestBody Employee employee,
	        BindingResult bindingResult) {

	    if(bindingResult.hasErrors()) {
	        Map<String, String> errors = new HashMap<>();
	        for (FieldError fieldError : bindingResult.getFieldErrors()) {
	            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
	        }
	        
	        return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
	    }

	    Employee saved = employeeService.createOrUpdateEmployee(employee, id);

	   
	    return ResponseEntity.ok(saved);
	}


	
@GetMapping(path = "/{depId}/{empId}")
public Employee getEmployeeById(@PathVariable Long depId,@PathVariable Long empId) {
	return employeeService.getEmployee(depId, empId);
}

@GetMapping(path = "/all/{id}")
public List<Employee> getAll(@PathVariable Long id){
	return employeeService.getEmployeeList(id);
}
@PutMapping("/{depId}/{empId}")
public ResponseEntity<?> updateEmployee(
        @PathVariable Long depId,
        @PathVariable Long empId,
        @RequestBody Employee employee) {

    employee.setId(empId);

    return ResponseEntity.ok(
        employeeService.createOrUpdateEmployee(employee, depId)
    );
}


@DeleteMapping(path = "/delete/{departmentId}/{id}")
public ResponseEntity<?> deleteEmployeeById(
        @PathVariable Long departmentId,
        @PathVariable Long id
) {
    return employeeService.deleteEmployeeById(departmentId, id);
}



}
