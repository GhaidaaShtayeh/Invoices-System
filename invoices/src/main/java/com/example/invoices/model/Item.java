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

    @ManyToMany
    private Set<Invoice> invoices;

    public Item(){}
    public Item(long serialNumber, String name, int unitPrice, Timestamp craetedDate){
        this.serialNumber = serialNumber;
        this.name = name;
        this.unitPrice = unitPrice;
        this.createdDate = craetedDate;
        this.isDeleted = false;
    }

}
