package com.example.invoices.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

    @ManyToMany()
    @JoinTable(
            name = "invoice_item",
            joinColumns = @JoinColumn(name = "item_id "),
            inverseJoinColumns = @JoinColumn(name = "invoice_id ")
    )
    private Set<Item> items;

    public Invoice(long serialNumber, String status, Timestamp createdDate) {
        this.serialNumber = serialNumber;
        this.status = status;
        this.createdDate = createdDate;
        this.isDeleted = false;
    }
    public Invoice(){}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Set<InvoiceHistory> getInvoiceHistories() {
        return invoiceHistories;
    }
    public void setInvoiceHistories(Set<InvoiceHistory> invoiceHistories) {
        this.invoiceHistories = invoiceHistories;
    }
    public Set<Item> getItems() {
        return items;
    }
    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
