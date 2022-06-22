package com.example.invoices.dto;

import com.example.invoices.model.Invoice;
import lombok.Data;

import java.util.Set;

@Data
public class CustomerDTO {


    private int id;
    private long serialNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private boolean isDeleted;
    private Set <Invoice> invoices;

    public CustomerDTO(){}
    public CustomerDTO(long serialNumber , String firstName, String lastName, String email, String mobileNumber) {
        this.serialNumber = serialNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.isDeleted = false;
    }

}
