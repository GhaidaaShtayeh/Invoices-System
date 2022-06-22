package com.example.invoices.repository;

import com.example.invoices.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional <Employee> findByEmail(String email);
}
