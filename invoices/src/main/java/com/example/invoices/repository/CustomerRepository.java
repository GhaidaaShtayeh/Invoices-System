package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByIsDeletedFalse();

}
