package org.makerminds.javaweb.service;

import java.util.List;

import org.makerminds.javaweb.entity.Employee;
import org.makerminds.javaweb.entity.Task;
import org.makerminds.javaweb.exceptions.TaskNotFoundException;
import org.makerminds.javaweb.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;


    public Task createNewTask(Task newTask, Long employeeId, Long departmentId) {

        Employee employee = employeeService.getEmployee(departmentId, employeeId);

        newTask.setEmployee(employee);

        if (newTask.getId() == null) {
            newTask.setStatus("INPUT QUEUE");
        }

        return taskRepository.save(newTask);
    }

  
    public List<Task> getTaskList(Long departmentId, Long employeeId) {

        Employee employee = employeeService.getEmployee(departmentId, employeeId);
        employee.getTaskList().size(); // detyron load për LAZY
        return employee.getTaskList();
    }

 
    public Task getTask(Long departmentId, Long employeeId, Long taskId) {

        Employee employee = employeeService.getEmployee(departmentId, employeeId);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getEmployee().getId().equals(employee.getId())) {
            throw new TaskNotFoundException("Task not found.");
        }

        return task;
    }

 
    public Task updateTask(Long departmentId, Long employeeId, Long taskId, Task updatedTask) {

        Employee employee = employeeService.getEmployee(departmentId, employeeId);

        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!existingTask.getEmployee().getId().equals(employee.getId())) {
            throw new TaskNotFoundException("Task not found.");
        }

        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setAcceptanceCriteria(updatedTask.getAcceptanceCriteria());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepository.save(existingTask);
    }
      
    @Transactional
    public ResponseEntity<?> deleteTask(Long departmentId, Long employeeId, Long taskId) {

        Employee employee = employeeService.getEmployee(departmentId, employeeId);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getEmployee().getId().equals(employee.getId())) {
            throw new TaskNotFoundException("Task not found.");
        }

        employee.getTaskList().remove(task);
        taskRepository.delete(task);

        return ResponseEntity.ok("Task with id: " + taskId + " has been deleted");
    }
}

