package com.example.invoices.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class InvoiceDTO {
    private int id;
    private long serialNumber;
    private String status;
    //private Timestamp createdDate;
    private long customerSerialNumber;
    private long employeeSerialNumber;
    private boolean isDeleted;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

  /*  public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }*/

    public long getCustomerSerialNumber() {
        return customerSerialNumber;
    }

    public long getEmployeeSerialNumber() {
        return employeeSerialNumber;
    }

    public void setCustomerSerialNumber(long customerSerialNumber) {
        this.customerSerialNumber = customerSerialNumber;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}

