package com.example.invoices.dto;

import com.example.invoices.model.Invoice;
import com.example.invoices.model.Item;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class InvoiceItemDTO {
    private int id;
    private int quantity;
    private long invoiceSerialNumber;
    private long itemSerialNumber;

    public InvoiceItemDTO(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getInvoiceSerialNumber() {
        return invoiceSerialNumber;
    }

    public long getItemSerialNumber() {
        return itemSerialNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
