package com.example.invoices.controller;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.service.CustomerServiceImpl;
import com.example.invoices.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceServiceImpl invoiceService;


    @GetMapping("/viewList")
    public ResponseEntity<List<Invoice>> getAllCustomers() {
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
            Invoice newInvoice = invoiceService
                    .saveInvoice(new Invoice(invoice.getSerialNumber(), invoice.getStatus(), invoice.getCreatedDate()));
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Invoice> updateCustomer(@PathVariable(value = "id") int id, @RequestBody InvoiceDTO invoice) {

        Invoice newInvoice = invoiceService.updateInvoice(id, invoice);

        return new ResponseEntity<Invoice>(newInvoice, HttpStatus.OK);
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
