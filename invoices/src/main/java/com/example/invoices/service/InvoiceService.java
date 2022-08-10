package com.example.invoices.service;

import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;

import java.util.List;

public interface InvoiceService {
    public Invoice saveInvoice(InvoiceDTO invoice);
    public List<Invoice> getInvoice();

    Invoice  getInvoiceBySerialNumber(long serialNumber);

    public Invoice updateInvoice(int invoiceId, InvoiceDTO invoiceDetails);
    public boolean deleteInvoice(int invoiceId);
    public Invoice getInvoice(long serialNumber);
    public List<Invoice> getInvoice(String token);

}
