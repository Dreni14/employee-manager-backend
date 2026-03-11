package org.makerminds.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

public class EmployeeManagerApplication {

	public static void main(String[] args) {
	SpringApplication.run(EmployeeManagerApplication.class, args);	
	}

} 
