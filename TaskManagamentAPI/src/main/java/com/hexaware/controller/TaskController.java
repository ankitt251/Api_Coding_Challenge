package com.hexaware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.exception.TaskNotFoundException;
import com.hexaware.model.Task;
import com.hexaware.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService ser;

	@GetMapping
	public List<Task> getTasks() {
		return ser.getAllTasks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) throws TaskNotFoundException {
		Task task = ser.getTaskById(id);
		if (task != null) {
			return new ResponseEntity<>(task, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addTask")
	public ResponseEntity<Task> createTask(@RequestBody Task task) throws TaskNotFoundException {
		task = ser.createTask(task);
		if (task == null) {
			return new ResponseEntity<>(task, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}

	@PutMapping("/updateTask/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) throws TaskNotFoundException {
		task = ser.updateTask(id, task);
		if (task == null) {
			return new ResponseEntity<>(task, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(task, HttpStatus.OK);
	}

	@DeleteMapping("/deleteTask/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id) throws TaskNotFoundException {
		ser.deleteTask(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}

}
