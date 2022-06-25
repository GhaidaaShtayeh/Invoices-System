package com.example.invoices.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "invoice_history")
public class InvoiceHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "updated_date")
    private Timestamp updatedDate;
    @Transient
    @Column(name = "invoice_history")
    private InvoiceItem invoiceHistory;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    public InvoiceItem getInvoiceHistory() {
        return invoiceHistory;
    }
    public void setInvoiceHistory(InvoiceItem invoiceHistory) {
        this.invoiceHistory = invoiceHistory;
    }
}
