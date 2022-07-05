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
import com.example.invoices.service.InvoiceHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/invoicehistory")
public class InvoicesHistoryController {
    @Autowired
    InvoiceHistoryServiceImpl invoiceHistoryService;


    @PostMapping("/save")
    public ResponseEntity<InvoiceHistory> addHistory(@RequestBody Invoice invoice) {
            Date updatedDate = new Date(); //to save updated date using today date
            InvoiceHistoryDTO invoiceHistoryDTO = new InvoiceHistoryDTO(new Timestamp(updatedDate.getTime()),invoice,invoice.getId(), invoice.getEmployeeId());
            InvoiceHistory newInvoice = invoiceHistoryService.saveInvoiceHistory(invoiceHistoryDTO);
        LOGGER.info("add history into invoice with id : "+invoice.getId()+" in : " + newInvoice.getUpdatedDate());
        return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
    }


    @GetMapping("/get-invoice-history/{serialNumber}")
    public ResponseEntity<?> getInvoice(@PathVariable long serialNumber){
        List<InvoiceHistory> invoice = invoiceHistoryService.getInvoice(serialNumber);
        LOGGER.info("get history for invoice with id : "+serialNumber);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
}
