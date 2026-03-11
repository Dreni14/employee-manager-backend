package org.makerminds.javaweb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.javaweb.entity.Task;
import org.makerminds.javaweb.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin()
public class TaskController {

    private final TaskService taskService;

  
    @PostMapping("/{departmentId}/{employeeId}")
    public ResponseEntity<?> addNewTask(
            @PathVariable Long departmentId,
            @PathVariable Long employeeId,
            @Valid @RequestBody Task newTask,
            BindingResult results) {

        if (results.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : results.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Task createdTask = taskService.createNewTask(newTask, employeeId, departmentId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }


    @GetMapping("/{departmentId}/{employeeId}")
    public List<Task> getTaskList(
            @PathVariable Long departmentId,
            @PathVariable Long employeeId) {

        return taskService.getTaskList(departmentId, employeeId);
    }


    @GetMapping("/{departmentId}/{employeeId}/{taskId}")
    public ResponseEntity<?> getTask(
            @PathVariable Long departmentId,
            @PathVariable Long employeeId,
            @PathVariable Long taskId) {

        return new ResponseEntity<>(
                taskService.getTask(departmentId, employeeId, taskId),
                HttpStatus.OK);
    }

        @PutMapping("/{departmentId}/{employeeId}/{taskId}")
    public ResponseEntity<?> updateTask(
            @PathVariable Long departmentId,
            @PathVariable Long employeeId,
            @PathVariable Long taskId,
            @Valid @RequestBody Task updatedTask,
            BindingResult results) {

        if (results.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : results.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Task task = taskService.updateTask(departmentId, employeeId, taskId, updatedTask);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    
    @DeleteMapping("/delete/{departmentId}/{employeeId}/{taskId}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long departmentId,
            @PathVariable Long employeeId,
            @PathVariable Long taskId) {

        return taskService.deleteTask(departmentId, employeeId, taskId);
    }
}
