package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import com.example.invoices.model.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceHistoryRepository extends JpaRepository<InvoiceHistory, Integer> {
}
