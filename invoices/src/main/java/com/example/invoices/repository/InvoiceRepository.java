package com.example.invoices.repository;

import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
     Invoice findBySerialNumber(long serialNumber);

}
