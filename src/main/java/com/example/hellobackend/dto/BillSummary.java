package com.example.hellobackend.dto;

public class BillSummary {
    private Long id;
    private String description;
    private Double amount;

    public BillSummary(Long id, String description, Double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }
}
