package com.example.invoices.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "serial_number")
    private long serialNumber;
    @Column(name = "status")
    private String status;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany
    private Set<InvoiceHistory> invoiceHistories;

    @ManyToMany
    private Set<Item> items;

}
