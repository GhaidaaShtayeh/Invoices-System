package com.example.invoices.service;

import com.example.invoices.dto.InvoiceHistoryDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.InvoiceHistory;
import com.example.invoices.repository.InvoiceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceHistoryServiceImpl implements InvoiceHistoryService {
    @Autowired
    InvoiceHistoryRepository invoiceHistoryRepository;
    @Override
    public InvoiceHistory saveInvoiceHistory(InvoiceHistory invoiceHistory) {

        InvoiceHistory newCustomer = invoiceHistoryRepository.save((InvoiceHistory) invoiceHistory);
        return newCustomer;
    }

    @Override
    public InvoiceHistory getInvoiceHistory(InvoiceHistory invoiceHistory) {
        return null;
    }
}
