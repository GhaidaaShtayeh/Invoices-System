package com.example.invoices.controller;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.dto.InvoiceHistoryDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.CustomerRepository;
import com.example.invoices.repository.EmployeeRepository;
import com.example.invoices.repository.InvoiceRepository;
import com.example.invoices.service.CustomerServiceImpl;
import com.example.invoices.service.InvoiceHistoryService;
import com.example.invoices.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceServiceImpl invoiceService;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    InvoiceHistoryService invoiceHistoryService;
    @Autowired
    InvoicesHistoryController invoicesHistoryController;

    @GetMapping("/dashboard")
    public List<Invoice> getAll (@RequestParam String field, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Direction.DESC, field);
        return invoiceRepository.findAll(pageable).toList();
    }

    @GetMapping("/viewList")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        invoices = invoiceService.getInvoice();
        if (invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDTO invoice) {
    try {
            Customer customer= customerRepository.findBySerialNumber(invoice.getCustomerSerialNumber());
            Employee employee = employeeRepository.findBySerialNumber(invoice.getEmployeeSerialNumber());
            Invoice newInvoice = invoiceService
                    .saveInvoice(new Invoice(invoice.getSerialNumber(), invoice.getStatus(), invoice.getCreatedDate(),employee,customer));
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Invoice> updateCustomer(@PathVariable(value = "id") int id, @RequestBody InvoiceDTO invoice) {
    try{
        Invoice newInvoice = invoiceService.updateInvoice(id, invoice);
       // System.out.println("update $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        Date updatedDate = new Date();
        InvoiceHistoryDTO invoiceHistoryDTO = new InvoiceHistoryDTO(new Timestamp(updatedDate.getTime()),newInvoice,newInvoice.getId(), newInvoice.getEmployeeId());
      //  System.out.println("histo1 ###############################################");
        invoicesHistoryController.addHistory(invoiceHistoryDTO);
      //  System.out.println("affffffffffffffffffffffffffffffffffffffffffffffffffff");
        return new ResponseEntity<Invoice>(newInvoice, HttpStatus.OK);
    }
    catch(Exception exception){
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable @RequestBody int invoiceId) {
        Optional<Invoice> invoiceData = Optional.ofNullable(invoiceService.getInvoice().get(invoiceId));
        if (invoiceData.isPresent()) {
            return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deleteInvoice/{invoiceId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int invoiceId) {

        if (invoiceId > 0) {
            boolean deleteStatus = invoiceService.deleteInvoice(invoiceId);
            if (deleteStatus) {
                return new ResponseEntity<String>("Customer deleted succeessfully.", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<String>("Customer not deleted .", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
