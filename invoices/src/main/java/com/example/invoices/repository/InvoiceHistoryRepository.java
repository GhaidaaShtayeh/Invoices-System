package com.example.invoices.repository;

import com.example.invoices.model.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, Integer> {
    List<InvoiceHistory> findAllByInvoiceSerialNumber(long serialNumber);
}
