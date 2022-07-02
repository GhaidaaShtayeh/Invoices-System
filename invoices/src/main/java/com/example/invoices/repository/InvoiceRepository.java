package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
     Invoice findBySerialNumber(long serialNumber);

     @Query(
             value = "select * from Invoice where is_deleted = false",
             nativeQuery = true)
     List<Invoice> getAllInvoices();

}
