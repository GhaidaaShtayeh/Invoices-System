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
import com.example.invoices.service.*;
import com.example.invoices.utilite.FileUpload;
import com.example.invoices.utilite.SetHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@CrossOrigin(origins = "*")
@Configuration
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
    InvoicesHistoryController invoicesHistoryController;
    @Autowired
    EmployeeServiceImpl employeeService;


    @GetMapping("/dashboard")
    public List<Invoice> getAll (@RequestParam String field, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Direction.DESC, field);
        LOGGER.info(" get All pageable Api controller are calling  " + page + " page number and size " + size + "sorting dsc according to  " + field);
        return invoiceService.findAllInvoices(pageable);
    }

    @GetMapping("/search")
    public Invoice getInvoiceValue(@RequestBody long invoiceId){
        Invoice invoice = invoiceService.getInvoiceBySerialNumber(invoiceId);
        LOGGER.info(" search Api controller are calling  foe serialNumber : " +invoiceId);
        return invoice;
    }


    @GetMapping("/viewList")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getInvoice();
        LOGGER.info(" list of invoices displayed controller calling ");
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDTO invoice) throws IOException {
        Invoice savedInvoice = invoiceService.saveInvoice(invoice);
        LOGGER.info(" invoice saved  " + invoice.getSerialNumber() + "and id : " + invoice.getId() + "from controller");
        return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id") int id, @RequestBody InvoiceDTO invoice) {
        Invoice newInvoice = invoiceService.updateInvoice(id, invoice);
        invoicesHistoryController.addHistory(newInvoice);
        LOGGER.info(" history added into invoice " + invoice.getSerialNumber() + " serial from controller");
        LOGGER.info(" invoice id : "+id+" updated from controller");
        return new ResponseEntity<Invoice>(newInvoice, HttpStatus.OK);
    }

    @GetMapping("/getCustomer/{invoiceId}")
    public ResponseEntity<?> getCustomer(@PathVariable @RequestBody int invoiceId) {
        Optional<Invoice> invoiceData = Optional.ofNullable(invoiceService.getInvoice().get(invoiceId));
        LOGGER.info(" get customer with invoice id   " + invoiceId + " are calling from controller ");
        return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
    }

    @GetMapping("/deleteInvoice/{invoiceId}")
    public boolean deleteInvoice(@PathVariable int invoiceId) {
            boolean deleteStatus = invoiceService.deleteInvoice(invoiceId);
                LOGGER.info(" invoice deleted " + invoiceId + " id ");
                return true;
    }

    @GetMapping("/get-invoice/{serialNumber}")
    public ResponseEntity<?> getInvoice(@PathVariable long serialNumber){
            Invoice invoice = invoiceService.getInvoice(serialNumber);
        LOGGER.info(" get All invoice with id " + serialNumber +" details Api are calling from controller");
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @GetMapping("/getInvoices")
    public ResponseEntity<?> getInvoiceByEmployee(@RequestHeader (name="Authorization") String token) {
        List<Invoice> invoices = invoiceService.getInvoice(token);
        LOGGER.info(" list of invoices displayed controller calling ");
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

}
