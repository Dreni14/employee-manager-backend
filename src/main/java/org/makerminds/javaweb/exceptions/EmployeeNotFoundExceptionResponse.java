package org.makerminds.javaweb.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNotFoundExceptionResponse {
	
	private String EmployeeNotFoundMessage;

	public EmployeeNotFoundExceptionResponse(String employeeNotFoundMessage) {
		super();
		EmployeeNotFoundMessage = employeeNotFoundMessage;
	}


	
	

}
