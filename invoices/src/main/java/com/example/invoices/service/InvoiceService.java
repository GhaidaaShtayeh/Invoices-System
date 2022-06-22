package com.example.invoices.service;

import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;

public interface InvoiceService {
    public Invoice saveInvoice(Invoice invoice);
    public Invoice getInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(int invoiceId);
}
