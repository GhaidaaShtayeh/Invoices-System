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
    private Invoice invoice;
    private Item item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
