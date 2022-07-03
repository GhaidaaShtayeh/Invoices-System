package com.example.invoices.controller;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.dto.InvoiceHistoryDTO;
import com.example.invoices.exception.InvoiceNotFoundException;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.CustomerRepository;
import com.example.invoices.repository.EmployeeRepository;
import com.example.invoices.repository.InvoiceRepository;
import com.example.invoices.service.CustomerServiceImpl;
import com.example.invoices.service.InvoiceHistoryService;
import com.example.invoices.service.InvoiceServiceImpl;
import com.example.invoices.utilite.SetHeaders;
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

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private SetHeaders headers;

    public InvoiceController(){
        headers = new SetHeaders();
    }

    @Autowired
    InvoiceServiceImpl invoiceService;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoicesHistoryController invoicesHistoryController;

    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping("/dashboard")
    public List<Invoice> getAll (@RequestParam String field, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Direction.DESC, field);
        LOGGER.info(" page pagination calling ");
        return invoiceRepository.findAll(pageable).toList();
    }

    @GetMapping("/viewList")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getInvoice();
        if (invoices.isEmpty()) {
            LOGGER.error(" list are empty  ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOGGER.info(" list of invoices displayed ");
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDTO invoice) {
    try {
        Invoice newInvoice = invoiceService.saveInvoice(invoice);
        LOGGER.info(" invoice saved  " + invoice.getSerialNumber() + "and id : " + invoice.getId());
        return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
        LOGGER.info(" exception in saved invoice " + invoice.getSerialNumber()+" caused by : " + e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id") int id, @RequestBody InvoiceDTO invoice) {
    try{
        Invoice newInvoice = invoiceService.updateInvoice(id, invoice);
        invoicesHistoryController.addHistory(newInvoice);
        LOGGER.info(" history added into invoice " + invoice.getSerialNumber() + " serial ");
        LOGGER.info(" invoice updated ");
        return new ResponseEntity<Invoice>(newInvoice, HttpStatus.OK);
    }
    catch(Exception exception){
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCustomer/{invoiceId}")
    public ResponseEntity<?> getCustomer(@PathVariable @RequestBody int invoiceId) {
        Optional<Invoice> invoiceData = Optional.ofNullable(invoiceService.getInvoice().get(invoiceId));
        if (invoiceData.isPresent()) {
            return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deleteInvoice/{invoiceId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable @RequestBody int invoiceId) {
        if (invoiceId > 0) {
            boolean deleteStatus = invoiceService.deleteInvoice(invoiceId);
            if (deleteStatus) {
                return new ResponseEntity<String>("invoice deleted succeessfully.", headers.Headers(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<String>("invoice not deleted .", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/get-invoice/{serialNumber}")
    public ResponseEntity<?> getInvoice(@PathVariable long serialNumber){
        try{
            Invoice invoice = invoiceService.getInvoice(serialNumber);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }catch(InvoiceNotFoundException invoiceNotFoundException){
            return new ResponseEntity<String>("invoice not found .", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getInvoicesEmployee/{serialNumber}")
    public ResponseEntity<?> getInvoiceByEmployee(@PathVariable long serialNumber){
        try{
            Employee employee = employeeRepository.findBySerialNumber(serialNumber);
            List<Invoice> invoice = invoiceService.getAllInvoicesByEmpId(employee);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        }catch(InvoiceNotFoundException invoiceNotFoundException){
            return new ResponseEntity<String>("invoices not found .", HttpStatus.NOT_FOUND);
        }

    }

}
