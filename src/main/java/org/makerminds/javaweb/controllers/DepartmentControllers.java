package org.makerminds.javaweb.controllers;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.javaweb.entity.Department;

import org.makerminds.javaweb.repositories.DepartmentRepository;
import org.makerminds.javaweb.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin()
@RequiredArgsConstructor
public class DepartmentControllers {

	private final DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<?> createDepartment(@Valid@RequestBody Department department,BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			Map<String,String> errors = new HashMap<>();
			for(FieldError fieldError : bindingResult.getFieldErrors()) {
			errors.put(fieldError.getField(),fieldError.getDefaultMessage());
		}
			return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(departmentService.createDepartment(department));
	}
	
	@DeleteMapping(path = "/delete/{id}") 
	public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
		return departmentService.deleteDepartmentById(id);
	}
	
	@GetMapping(path = "/all")
	public Iterable<Department> getDepartments(){
		return departmentService.getDepartments();
	}
	
	@GetMapping(path = "/{id}")
	public Department getDepartmentById(@PathVariable Long id) {
		return departmentService.findById(id);
	}
	@PutMapping("/{id}")
	public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {

	    Department existing = departmentService.findById(id);

	    existing.setName(department.getName());

	    return departmentService.createDepartment(existing);
	}

}
