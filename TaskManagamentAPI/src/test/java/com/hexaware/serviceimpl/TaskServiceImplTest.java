package com.hexaware.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.model.Priority_Enum;
import com.hexaware.model.Status_Enum;
import com.hexaware.model.Task;
import com.hexaware.repository.TaskRepository;

class TaskServiceImplTest {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	private Task task1;
	private Task task2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Dummy Task 1
		task1 = new Task();
		task1.setId(1L);
		task1.setTitle("Complete project report");
		task1.setDescription("Finalize and submit the report");
		task1.setDueDate(LocalDate.of(2024, 11, 30));
		task1.setPriority(Priority_Enum.HIGH);
		task1.setStatus(Status_Enum.IN_PROGRESS);

		// Dummy Task 2
		task2 = new Task();
		task2.setId(2L);
		task2.setTitle("Buy groceries");
		task2.setDescription("Vegetables, fruits, and essentials");
		task2.setDueDate(LocalDate.of(2024, 11, 20));
		task2.setPriority(Priority_Enum.LOW);
		task2.setStatus(Status_Enum.PENDING);
	}

	@Test
	void testGetAllTasks() {
		when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

		var tasks = taskService.getAllTasks();

		assertNotNull(tasks);
		assertEquals(2, tasks.size());
		verify(taskRepository, times(1)).findAll();
	}

	@Test
	void testGetTaskById_Success() {
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

		Task foundTask = taskService.getTaskById(1L);

		assertNotNull(foundTask);
		assertEquals("Complete project report", foundTask.getTitle());
		verify(taskRepository, times(1)).findById(1L);
	}

	@Test
	void testGetTaskById_NotFound() {
		when(taskRepository.findById(3L)).thenReturn(Optional.empty());

		Task foundTask = taskService.getTaskById(3L);

		assertNull(foundTask);
		verify(taskRepository, times(1)).findById(3L);
	}

	@Test
	void testCreateTask() {
		when(taskRepository.save(any(Task.class))).thenReturn(task1);

		Task newTask = new Task();
		newTask.setTitle("Prepare presentation");
		newTask.setDescription("Create slides for review meeting");
		newTask.setDueDate(LocalDate.of(2024, 11, 28));
		newTask.setPriority(Priority_Enum.MEDIUM);
		newTask.setStatus(Status_Enum.PENDING);

		Task savedTask = taskService.createTask(newTask);

		assertNotNull(savedTask);
		assertEquals("Complete project report", savedTask.getTitle());
		verify(taskRepository, times(1)).save(any(Task.class));
	}

	@Test
	void testUpdateTask() {
		when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
		when(taskRepository.save(any(Task.class))).thenReturn(task1);

		Task updatedTask = new Task();
		updatedTask.setTitle("Updated Title");
		updatedTask.setDescription("Updated Description");
		updatedTask.setDueDate(LocalDate.of(2024, 12, 15));
		updatedTask.setPriority(Priority_Enum.MEDIUM);
		updatedTask.setStatus(Status_Enum.COMPLETED);

		Task result = taskService.updateTask(1L, updatedTask);

		assertNotNull(result);
		assertEquals("Updated Title", result.getTitle());
		assertEquals("Updated Description", result.getDescription());
		assertEquals(LocalDate.of(2024, 12, 15), result.getDueDate());
		assertEquals(Priority_Enum.MEDIUM, result.getPriority());
		assertEquals(Status_Enum.COMPLETED, result.getStatus());
		verify(taskRepository, times(1)).findById(1L);
		verify(taskRepository, times(1)).save(any(Task.class));
	}

	@Test
	void testDeleteTask() {
		doNothing().when(taskRepository).deleteById(1L);

		taskService.deleteTask(1L);

		verify(taskRepository, times(1)).deleteById(1L);
	}
}
