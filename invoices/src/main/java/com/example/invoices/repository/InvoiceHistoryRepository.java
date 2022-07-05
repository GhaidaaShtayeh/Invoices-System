package com.example.invoices.repository;

import com.example.invoices.model.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, Integer> {
    List<InvoiceHistory> findAllByInvoiceSerialNumber(long serialNumber);
}
