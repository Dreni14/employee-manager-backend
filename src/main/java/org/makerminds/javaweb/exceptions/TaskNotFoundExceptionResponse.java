package org.makerminds.javaweb.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskNotFoundExceptionResponse {
	
	private String TaskNotFoundMessage;

	public TaskNotFoundExceptionResponse(String taskNotFoundMessage) {
		super();
		TaskNotFoundMessage = taskNotFoundMessage;
	}
	
	
	

}
