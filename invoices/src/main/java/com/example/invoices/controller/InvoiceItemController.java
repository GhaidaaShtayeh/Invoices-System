package com.example.invoices.controller;


import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.dto.InvoiceItemDTO;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;
import com.example.invoices.model.Item;
import com.example.invoices.repository.InvoiceRepository;
import com.example.invoices.repository.ItemRepository;
import com.example.invoices.service.InvoiceItemService;
import com.example.invoices.service.InvoiceItemServiceImpl;
import com.example.invoices.service.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoiceItem")
public class InvoiceItemController {

    @Autowired
    InvoiceItemServiceImpl invoiceService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    @GetMapping("/viewList")
    public ResponseEntity<List<InvoiceItem>> getAllCustomers() {
        List<InvoiceItem> invoices = new ArrayList<>();
        invoices = invoiceService.getAllInvoiceItem();
        if (invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<InvoiceItem> addInvoice(@RequestBody InvoiceItemDTO invoiceItem) {

        try {

            Invoice invoice = invoiceRepository.findBySerialNumber(invoiceItem.getInvoiceSerialNumber());
            Item item = itemRepository.findBySerialNumber(invoiceItem.getItemSerialNumber());
            InvoiceItem newInvoice = invoiceService
                    .saveInvoiceItem(new InvoiceItem(invoiceItem.getQuantity(),invoice,item));
            return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<InvoiceItem> updateCustomer(@PathVariable(value = "id") int id, @RequestBody InvoiceItemDTO invoice) {

        InvoiceItem newInvoice = invoiceService.updateInvoiceItem(id, invoice);

        return new ResponseEntity<InvoiceItem>(newInvoice, HttpStatus.OK);
    }


    /*@PutMapping("/deleteInvoice/{invoiceId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int invoiceId) {

        if (invoiceId > 0) {
            boolean deleteStatus = invoiceService.deleteInvoiceItem(invoiceId);
            if (deleteStatus) {
                return new ResponseEntity<String>("Customer deleted succeessfully.", HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<String>("Customer not deleted .", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
*/
}
