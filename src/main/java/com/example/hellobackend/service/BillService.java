package com.example.hellobackend.service;

import com.example.hellobackend.model.Bill;
import com.example.hellobackend.model.User;
import com.example.hellobackend.dto.IncomingBill;
import com.example.hellobackend.dto.BillSummary;
import com.example.hellobackend.dto.MonthlyBillGroup;
import com.example.hellobackend.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

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

    public List<MonthlyBillGroup> getBillsGroupedByMonth(User user) {
        List<Bill> bills = repository.findByUser(user);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        Map<String, List<Bill>> groupedBills = bills.stream()
                .collect(Collectors.groupingBy(bill -> bill.getDate().format(formatter)));

        return groupedBills.entrySet().stream()
                .map(entry -> {
                    String month = entry.getKey();
                    List<Bill> monthBills = entry.getValue();

                    Double totalAmount = monthBills.stream()
                            .mapToDouble(Bill::getAmount)
                            .sum();

                    List<BillSummary> billSummaries = monthBills.stream()
                            .map(bill -> new BillSummary(bill.getId(), bill.getDescription(), bill.getAmount()))
                            .collect(Collectors.toList());

                    return new MonthlyBillGroup(month, totalAmount, billSummaries);
                })
                .sorted((a, b) -> b.getMonth().compareTo(a.getMonth())) // Sort by month descending
                .collect(Collectors.toList());
    }

}
