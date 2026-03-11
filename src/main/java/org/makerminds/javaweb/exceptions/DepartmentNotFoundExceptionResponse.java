package org.makerminds.javaweb.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentNotFoundExceptionResponse {

	private String DepartmentNotFoundmessage;
	
	public  DepartmentNotFoundExceptionResponse(String DepartmentNotFoundmessage) {
		super();
		this.DepartmentNotFoundmessage = DepartmentNotFoundmessage;
	}
	
	
}
