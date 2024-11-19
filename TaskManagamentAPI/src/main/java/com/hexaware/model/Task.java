package com.hexaware.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Title is required")
	private String title;

	private String description;

	@NotNull(message = "Due date is required")
	private LocalDate dueDate;

	private Priority_Enum priority;

	private Status_Enum status;

}
