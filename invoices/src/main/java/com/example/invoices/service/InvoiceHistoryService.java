package com.example.invoices.service;

import com.example.invoices.dto.InvoiceHistoryDTO;
import com.example.invoices.model.Employee;
import com.example.invoices.model.InvoiceHistory;

public interface InvoiceHistoryService {
    public InvoiceHistory saveInvoiceHistory(InvoiceHistory invoiceHistory);
    public InvoiceHistory getInvoiceHistory(InvoiceHistory invoiceHistory);
}
