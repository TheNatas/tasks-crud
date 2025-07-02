package com.example.hellobackend.service;

import com.example.hellobackend.model.Task;
import com.example.hellobackend.dto.TaskSummary;
import com.example.hellobackend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return repository.findAll();
    }

    public Task create(Task task) {
        return repository.save(task);
    }

    public Optional<Task> markDone(Long id) {
        return repository.findById(id).map(task -> {
            task.setDone(true);
            return repository.save(task);
        });
    }

    public List<Task> getDoneTasks() {
        return repository.findByDoneTrue();
    }

    public List<TaskSummary> getDoneSummaries() {
        return repository.findDoneTaskSummaries();
    }

}
