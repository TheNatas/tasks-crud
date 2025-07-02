package com.example.hellobackend.dto;

public class TaskSummary {
    private Long id;
    private String description;

    public TaskSummary(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
