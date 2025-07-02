package com.example.hellobackend.controller;

import com.example.hellobackend.model.Task;
import com.example.hellobackend.dto.TaskSummary;
import com.example.hellobackend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getAll() {
        return service.getAll();
    }

    @GetMapping("/done")
    public List<Task> getDoneTasks() {
        return service.getDoneTasks();
    }

    @GetMapping("/done/summary")
    public List<TaskSummary> getDoneSummaries() {
        return service.getDoneSummaries();
    }

    @PostMapping
    public Task create(@Valid @RequestBody Task task) {
        return service.create(task);
    }

    @PostMapping("/{id}/done")
    public Task markDone(@PathVariable Long id) {
        return service.markDone(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }
}