package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import com.example.invoices.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Integer> {
}
