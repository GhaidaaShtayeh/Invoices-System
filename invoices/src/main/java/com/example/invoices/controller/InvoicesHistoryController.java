package com.example.invoices.controller;

import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.dto.InvoiceHistoryDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceHistory;
import com.example.invoices.repository.EmployeeRepository;
import com.example.invoices.repository.InvoiceRepository;
import com.example.invoices.service.InvoiceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/invoicehistory")
public class InvoicesHistoryController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceHistoryService invoiceHistoryService;


    @PostMapping("/save")
    public ResponseEntity<InvoiceHistory> addHistory(@RequestBody Invoice invoice) {
        try {
            Date updatedDate = new Date();
            InvoiceHistoryDTO invoiceHistoryDTO = new InvoiceHistoryDTO(new Timestamp(updatedDate.getTime()),invoice,invoice.getId(), invoice.getEmployeeId());
            InvoiceHistory newInvoice = invoiceHistoryService
                    .saveInvoiceHistory(new InvoiceHistory(invoiceHistoryDTO.getUpdatedDate(), invoiceHistoryDTO.getInvoiceInfo(), invoiceHistoryDTO.getInvoiceInfo().toString(), invoiceHistoryDTO.getInvoiceInfo().getEmployee()));
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get-invoice-history/{serialNumber}")
    public ResponseEntity<?> getInvoice(@PathVariable long serialNumber){
        List<InvoiceHistory> invoice = invoiceHistoryService.getInvoice(serialNumber);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
}
