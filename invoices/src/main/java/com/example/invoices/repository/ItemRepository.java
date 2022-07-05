package com.example.invoices.repository;

import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
     Item findBySerialNumber(long serialNumber);
    @Query(
            value = "select * from Item where is_deleted = false",
            nativeQuery = true)
    List<Item> getAllItems();

    boolean existsBySerialNumber(long serialNumber);
}
