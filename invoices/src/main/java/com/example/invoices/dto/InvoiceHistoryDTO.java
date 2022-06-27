package com.example.invoices.dto;

import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class InvoiceHistoryDTO {
    private int id;
    private Timestamp updatedDate;
    private Invoice invoiceInfo;

    private long invoiceSerialNumber;
    private long employeeSerialNumber;


    public InvoiceHistoryDTO(Timestamp updatedDate , Invoice invoiceInfo , long invoiceSerialNumber , long employeeSerialNumber ){
        this.updatedDate = updatedDate;
        this.invoiceInfo = invoiceInfo;
        this.invoiceSerialNumber = invoiceSerialNumber;
        this.employeeSerialNumber = employeeSerialNumber;
    }
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public long getInvoiceSerialNumber() {
        return invoiceSerialNumber;
    }

    public Invoice getInvoiceInfo() {
        return invoiceInfo;
    }

    public long getEmployeeSerialNumber() {
        return employeeSerialNumber;
    }

    public int getId() {
        return id;
    }
}
