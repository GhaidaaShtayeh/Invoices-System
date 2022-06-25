package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import com.example.invoices.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {
}
