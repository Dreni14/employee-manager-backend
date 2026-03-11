package org.makerminds.javaweb.repositories;

import java.util.List;
import java.util.Optional;

import org.makerminds.javaweb.entity.Department;

import org.makerminds.javaweb.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long>{
	
	public Optional<Department> findById(Long id);

}
