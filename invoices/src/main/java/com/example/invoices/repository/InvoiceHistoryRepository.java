package com.example.invoices.repository;

import com.example.invoices.model.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, Integer> {
}
