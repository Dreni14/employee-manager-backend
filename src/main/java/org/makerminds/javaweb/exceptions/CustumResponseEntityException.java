package org.makerminds.javaweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustumResponseEntityException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public final ResponseEntity<?> handleDepartmentNotFoundException(DepartmentNotFoundException ex){
		DepartmentNotFoundExceptionResponse departmentNotFoundExceptionResponse = new DepartmentNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(departmentNotFoundExceptionResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler
	public final ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		EmployeeNotFoundExceptionResponse employeeNotFoundExceptionResponse = new EmployeeNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(employeeNotFoundExceptionResponse,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler
	public final ResponseEntity<?> handleTaskNotFoundException(TaskNotFoundException ex){
		TaskNotFoundExceptionResponse taskNotFoundExceptionResponse = new TaskNotFoundExceptionResponse(ex.getMessage());
		return new ResponseEntity<>(taskNotFoundExceptionResponse,HttpStatus.BAD_REQUEST);
		
	}
	  
	
		
	

}
