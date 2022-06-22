package com.example.invoices.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class InvoiceDTO {
    private int id;
    private long serialNumber;
    private String status;
    private Timestamp createdDate;
    private boolean isDeleted;

}
