package com.example.hellobackend.dto;

import java.util.List;

public class MonthlyBillGroup {
    private String month; // Format: "YYYY-MM"
    private Double totalAmount;
    private List<BillSummary> bills;

    public MonthlyBillGroup(String month, Double totalAmount, List<BillSummary> bills) {
        this.month = month;
        this.totalAmount = totalAmount;
        this.bills = bills;
    }

    public String getMonth() {
        return month;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public List<BillSummary> getBills() {
        return bills;
    }
}