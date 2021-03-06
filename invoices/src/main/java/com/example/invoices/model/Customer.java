package com.example.invoices.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "serial_number")
    private long serialNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "is_deleted")
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    @OneToMany(fetch=FetchType.EAGER, mappedBy="customer")
    private Set <Invoice> invoices;

    public Customer(){}
    public Customer(long serialNumber , String firstName, String lastName, String email, String mobileNumber) {
        this.serialNumber = serialNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.deleted = false;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.firstName;
    }
    public Long getSerialNumber(Long serialNumber) {
        return this.serialNumber;
    }
    public String getEmail() {
        return this.email;
    }
    public String getMobileNumber() {
        return this.mobileNumber;
    }
    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
	public String getLastName() {
		return lastName;
	}
	public Set<Invoice> getInvoices() {
		return invoices;
	}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}
