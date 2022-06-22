package com.example.invoices.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class AuthRequest {
    @Email  @Length(max = 50 , min = 5)
    private String email;

    @Length(max = 50 , min = 2)
    private  String password;

    public AuthRequest(String email ,String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
