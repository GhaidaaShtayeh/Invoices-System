package com.example.invoices.service;

import com.example.invoices.model.Employee;
import com.example.invoices.model.InvoiceItem;

public interface InvoiceItemService {
    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem);
    public InvoiceItem getInvoiceItem(InvoiceItem invoiceItem);
    public InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem);
    public InvoiceItem deleteInvoiceItem(int invoiceId);
}
