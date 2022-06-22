package com.example.invoices.dto;

import com.example.invoices.model.InvoiceItem;

import java.sql.Timestamp;

public class InvoiceHistoryDTO {
    private int id;
    private Timestamp updatedDate;
    private InvoiceItem invoiceHistory;

}
