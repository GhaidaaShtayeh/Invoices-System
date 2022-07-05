package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findBySerialNumber(long serialNumber);
    @Query(
            value = "select * from Customer where is_deleted = false",
            nativeQuery = true)
    List<Customer> getAllCustomers();
}
