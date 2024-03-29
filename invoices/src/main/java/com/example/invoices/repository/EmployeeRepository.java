package com.example.invoices.repository;

import com.example.invoices.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional <Employee> findByEmail(String email);
    Employee findBySerialNumber(long serialNumber);

}
