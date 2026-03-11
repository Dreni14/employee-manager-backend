package org.makerminds.javaweb;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makerminds.javaweb.service.DepartmentService;
import org.makerminds.javaweb.controllers.DepartmentControllers;
import org.makerminds.javaweb.entity.Department;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class EmployeeManagerApplicationTests {

	private MockMvc mockMvc;
	@MockBean
	
	private DepartmentService departmentService;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(new DepartmentControllers(departmentService)).build();
		
	}
	
	
	@Test
	void testGetDepartment() throws Exception {
		
		Department department1 =  new Department();
		department1.setId(1L);
		department1.setName("Department1");
		
		Department department2 =  new Department();
		department2.setId(2L);
		department2.setName("Department2");
		
		
		when(departmentService.getDepartments()).thenReturn(Arrays.asList(department1,department2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/departments/all"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Department1"))
		
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Department2"));
		
	}

}
