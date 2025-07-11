package com.example.hellobackend.controller;

import com.example.hellobackend.model.Bill;
import com.example.hellobackend.dto.BillSummary;
import com.example.hellobackend.service.BillService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:4200")
public class BillController {

    private final BillService service;

    public BillController(BillService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bill> getAll() {
        return service.getAll();
    }

    @GetMapping("/done")
    public List<Bill> getPaidBills() {
        return service.getPaidBills();
    }

    @GetMapping("/paid/summary")
    public List<BillSummary> getPaidSummaries() {
        return service.getPaidSummaries();
    }

    @PostMapping
    public Bill create(@Valid @RequestBody Bill bill) {
        return service.create(bill);
    }

    @PutMapping("/{id}/paid")
    public Bill markPaid(@PathVariable Long id) {
        return service.markPaid(id).orElseThrow(() -> new RuntimeException("Bill not found"));
    }
}