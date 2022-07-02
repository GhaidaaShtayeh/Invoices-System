package com.example.invoices.service;

import com.example.invoices.dto.InvoiceHistoryDTO;
import com.example.invoices.model.Employee;
import com.example.invoices.model.InvoiceHistory;

import java.util.List;

public interface InvoiceHistoryService {
    public InvoiceHistory saveInvoiceHistory(InvoiceHistory invoiceHistory);

    public List<InvoiceHistory> getInvoice(long serialNumber);
}
