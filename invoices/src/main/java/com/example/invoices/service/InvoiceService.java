package com.example.invoices.service;

import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;

import java.util.List;

public interface InvoiceService {
    public Invoice saveInvoice(Invoice invoice);
    public List<Invoice> getInvoice();
    public Invoice updateInvoice(int invoiceId, InvoiceDTO invoiceDetails);
    public boolean deleteInvoice(int invoiceId);
}
