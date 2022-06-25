package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice, Integer> {
}
