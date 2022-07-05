package com.example.invoices.service;

import com.example.invoices.dto.InvoiceItemDTO;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InvoiceItemService {
    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem);
    public List<InvoiceItem> getAllInvoiceItem(long serialNumber);
    public InvoiceItem updateInvoiceItem(int invoiceId, InvoiceItemDTO invoice);

}
