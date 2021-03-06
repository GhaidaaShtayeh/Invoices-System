package com.example.invoices.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "serial_number")
    private long serialNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "unit_price")
    private int unitPrice;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "is_deleted")
    private boolean isDeleted;

   /* @ManyToMany()
    @JoinTable(
            name = "invoice_item",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Invoice> invoices;*/

    @OneToMany (mappedBy = "invoice")
    private Set <InvoiceItem> quantity;

    public Item(){}
    public Item(long serialNumber, String name, int unitPrice){
        this.serialNumber = serialNumber;
        this.name = name;
        this.unitPrice = unitPrice;
        this.isDeleted = false;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Set<InvoiceItem> getQuantity() {
        return quantity;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
