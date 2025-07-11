package com.example.hellobackend.service;

import com.example.hellobackend.model.Bill;
import com.example.hellobackend.dto.BillSummary;
import com.example.hellobackend.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public List<Bill> getAll() {
        return repository.findAll();
    }

    public Bill create(Bill bill) {
        return repository.save(bill);
    }

    public Optional<Bill> markPaid(Long id) {
        return repository.findById(id).map(bill -> {
            bill.setPaid(true);
            return repository.save(bill);
        });
    }

    public List<Bill> getPaidBills() {
        return repository.findByPaidTrue();
    }

    public List<BillSummary> getPaidSummaries() {
        return repository.findPaidBillSummaries();
    }

}
