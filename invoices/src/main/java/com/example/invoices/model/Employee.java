package com.example.invoices.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "serial_number")
    private long serialNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
    private String email;
    private String mobileNumber;
    private String country;
    private String password;
    private boolean isDeleted;

    @OneToMany
    private Set<Invoice> invoices;
    @OneToMany
    private Set<InvoiceHistory> invoiceHistories;

    public Employee(){}
    public Employee(long serialNumber, String firstName, String lastName, Role role, String email, String mobileNumber,String country,String password){
        super();
        this.serialNumber = serialNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role= role;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.country = country;
        this.password = password;
        this.isDeleted = false;
    }

    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.firstName;
    }
    public Long getSerialNumber() {
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
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setId(int parseInt) {
        this.id = parseInt;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Role getRole() {
        return role;
    }
    public String getCountry() {
        return country;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


}