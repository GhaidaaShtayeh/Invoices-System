package com.example.invoices.repository;

import com.example.invoices.model.Employee;
import com.example.invoices.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByIsDeletedFalse();

     Item findBySerialNumber(long serialNumber);

}
