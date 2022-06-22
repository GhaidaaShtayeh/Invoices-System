package com.example.invoices.repository;

import com.example.invoices.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICutomerRepository extends JpaRepository<Customer, Integer> {
	/*
	 * @Modifying
	 * 
	 * @Query(value="update Book b set b.price = :price where b.id = :id",
	 * nativeQuery=true) public void updatePrice(@Param(value="price")float
	 * price, @Param(value="id") Integer id);
	 */
}
