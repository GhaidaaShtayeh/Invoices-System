package com.example.invoices.repository;

import com.example.invoices.model.InvoiceItem;
import com.example.invoices.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);
}
