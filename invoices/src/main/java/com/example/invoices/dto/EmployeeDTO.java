package com.example.invoices.dto;

import com.example.invoices.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class EmployeeDTO {


    private int id;
    private long serialNumber;
    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private String mobileNumber;
    private String country;
    private String password;
    private boolean isDeleted;


    public EmployeeDTO(int id, long serialNumber, String firstName, String lastName, Role role, String email,
                       String mobileNumber, String country, String password, boolean isDeleted) {
        super();
        this.id = id;
        this.serialNumber = serialNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.country = country;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public EmployeeDTO() {
        super();
    }

}