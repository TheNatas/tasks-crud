package com.example.hellobackend.repository;

import com.example.hellobackend.model.Bill;
import com.example.hellobackend.dto.BillSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
  List<Bill> findByPaidTrue();
  @Query("SELECT new com.example.hellobackend.dto.BillSummary(t.id, t.description, t.amount) FROM Bill t WHERE t.paid = true")
  List<BillSummary> findPaidBillSummaries();

}
