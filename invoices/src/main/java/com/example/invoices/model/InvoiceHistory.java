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
    private Invoice invoiceHistory;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public InvoiceHistory() {
    }

    public InvoiceHistory(Timestamp updatedDate, Invoice invoiceHistory, Employee employee){
    this.updatedDate = updatedDate;
    this.invoice = invoiceHistory;
    this.invoiceHistory = invoiceHistory;
    this.employee = employee;
}

}
