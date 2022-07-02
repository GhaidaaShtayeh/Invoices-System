package com.example.invoices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToMany(fetch=FetchType.EAGER, mappedBy="invoice")
    private Set <InvoiceHistory> invoiceHistories;
    @OneToMany (mappedBy = "invoice")
    private Set <InvoiceItem> quantity;

    public Invoice(long serialNumber, String status, Timestamp createdDate, Employee employee , Customer customer)
    {
        this.serialNumber = serialNumber;
        this.status = status;
        this.createdDate = createdDate;
        this.isDeleted = false;
        this.employee=employee;
        this.customer=customer;
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
    public int getEmployeeId(){
        return employee.getId();
    }
    public long getCustomerSerialNumber(){
        return customer.getId();
    }
    public String toString() {
        return ("invoice serial number : " + this.getSerialNumber() + "  customer id : "+this.getCustomerSerialNumber() + " invoice status :  "  + this.getStatus());
    }

}
