package org.makerminds.javaweb.repositories;

import java.util.Optional;

import org.makerminds.javaweb.entity.Department;
import org.makerminds.javaweb.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long>{
	
	public Optional<Task> findById(Long id);

}
