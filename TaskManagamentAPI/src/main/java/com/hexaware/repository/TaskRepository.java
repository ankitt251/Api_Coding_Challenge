package com.hexaware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
