package com.example.hellobackend.service;

import com.example.hellobackend.model.Bill;
import com.example.hellobackend.model.User;
import com.example.hellobackend.dto.IncomingBill;
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

    public List<Bill> getAllByUser(User user) {
        return repository.findByUser(user);
    }

    public Bill create(IncomingBill bill, User user) {
        Bill newBill = new Bill();
        newBill.setDescription(bill.getDescription());
        newBill.setAmount(bill.getAmount());
        newBill.setDate(bill.getDate());
        newBill.setUser(user);
        return repository.save(newBill);
    }

    public Optional<Bill> markPaid(Long id) {
        return repository.findById(id).map(bill -> {
            bill.setPaid(true);
            return repository.save(bill);
        });
    }

}
