package com.hexaware.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.model.Task;
import com.hexaware.repository.TaskRepository;
import com.hexaware.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repo;

	@Override
	public List<Task> getAllTasks() {
		return repo.findAll();
	}

	@Override
	public Task getTaskById(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
	}

	public Task createTask(Task task) {
		return repo.save(task);
	}

	public Task updateTask(Long id, Task updatedTask) {
		Task task = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

		if (updatedTask.getTitle() != null) {
			task.setTitle(updatedTask.getTitle());
		}

		if (updatedTask.getDescription() != null) {
			task.setDescription(updatedTask.getDescription());
		}

		if (updatedTask.getDueDate() != null) {
			task.setDueDate(updatedTask.getDueDate());
		}

		if (updatedTask.getPriority() != null) {
			task.setPriority(updatedTask.getPriority());
		}

		if (updatedTask.getStatus() != null) {
			task.setStatus(updatedTask.getStatus());
		}

		return repo.save(task);

	}

	public void deleteTask(Long id) {
		repo.deleteById(id);
	}

}
