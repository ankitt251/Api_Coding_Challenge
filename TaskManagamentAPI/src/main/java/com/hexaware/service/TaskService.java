package com.hexaware.service;

import java.util.List;

import com.hexaware.model.Task;

public interface TaskService {

	public List<Task> getAllTasks();

	public Task getTaskById(Long Id);

	public Task createTask(Task task);

	public Task updateTask(Long id, Task updatedTask);

	public void deleteTask(Long id);
}
