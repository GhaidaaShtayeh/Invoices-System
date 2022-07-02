package com.example.invoices.repository;

import com.example.invoices.model.InvoiceHistory;
import com.example.invoices.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {

    List<InvoiceItem> findAllByInvoiceSerialNumber(long serialNumber);

}
