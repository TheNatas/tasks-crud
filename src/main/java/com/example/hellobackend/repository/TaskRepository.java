package com.example.hellobackend.repository;

import com.example.hellobackend.model.Task;
import com.example.hellobackend.dto.TaskSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByDoneTrue();
  @Query("SELECT new com.example.hellobackend.dto.TaskSummary(t.id, t.description) FROM Task t WHERE t.done = true")
  List<TaskSummary> findDoneTaskSummaries();

}
