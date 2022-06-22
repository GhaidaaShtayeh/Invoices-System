package com.example.invoices.model;

import javax.persistence.*;

@Entity
@Table(name = "invoiceItem")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
