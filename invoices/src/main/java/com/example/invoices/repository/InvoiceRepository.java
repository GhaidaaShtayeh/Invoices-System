package com.example.invoices.repository;

import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

     Invoice findBySerialNumber(long serialNumber);

     @Query(
             value = "select * from Invoice where is_deleted = false",
             nativeQuery = true)
     List<Invoice> getAllInvoices(Pageable pageable);

     @Query(
             value = "select * from Invoice where is_deleted = false",
             nativeQuery = true)
     List<Invoice> getAllInvoices();



     @Query(
             value = "select * from  Invoice  x  where x.employee_id=:empId",
             nativeQuery = true)
     List<Invoice> findByEmployeeId(int empId);

}
