package com.example.hellobackend.controller;

import com.example.hellobackend.model.Bill;
import com.example.hellobackend.repository.UserRepository;
import com.example.hellobackend.dto.IncomingBill;
import com.example.hellobackend.dto.MonthlyBillGroup;
import com.example.hellobackend.service.BillService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "http://localhost:4200")
public class BillController {

    private final BillService service;
    private final UserRepository userRepository;

    public BillController(BillService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Bill> getAll(@AuthenticationPrincipal String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return service.getAllByUser(user);
    }

    @PostMapping
    public Bill create(
        @Valid @RequestBody IncomingBill bill,
        @AuthenticationPrincipal String username
    ) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return service.create(bill, user);
    }

    @PutMapping("/{id}/paid")
    public Bill markPaid(@PathVariable Long id) {
        return service.markPaid(id).orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    @GetMapping("/grouped-by-month")
    public List<MonthlyBillGroup> getBillsGroupedByMonth(@AuthenticationPrincipal String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return service.getBillsGroupedByMonth(user);
    }
}