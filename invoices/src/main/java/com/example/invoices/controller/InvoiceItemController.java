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
import com.example.invoices.service.ItemService;
import com.example.invoices.utilite.SetHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/invoiceItem")
/*
here are the controllers to manage items for specific invoice
*/
public class InvoiceItemController {
    @Autowired
    InvoiceItemServiceImpl invoiceService;
    @Autowired
    ItemService itemService;
    @Autowired
    InvoiceServiceImpl invoiceCardService;


    @GetMapping("/viewList/{serialNumber}")
    public ResponseEntity<List<InvoiceItem>> getAllCustomers(@PathVariable long serialNumber) {
        List<InvoiceItem> invoices = invoiceService.getAllInvoiceItem(serialNumber);
        LOGGER.info("get all items for invoices with serial number from controller" + serialNumber);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<InvoiceItem> addInvoice(@RequestBody InvoiceItemDTO invoiceItem) {
        Invoice invoice = invoiceCardService.getInvoiceBySerialNumber(invoiceItem.getInvoiceSerialNumber());
        LOGGER.info("get invoice serial number to add new item from controller : " + invoiceItem.getInvoiceSerialNumber());
        Item item = itemService.getItemBySerialNumber(invoiceItem.getItemSerialNumber());
        LOGGER.info("get item with serial number to add new item from controller : " + invoiceItem.getItemSerialNumber());
        InvoiceItem newInvoice = invoiceService.saveInvoiceItem(new InvoiceItem(invoiceItem.getQuantity(),invoice,item));
        LOGGER.info("saved item into invoice from controller ");
        return new ResponseEntity<>(newInvoice ,HttpStatus.CREATED);
    }


    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<InvoiceItem> updateCustomer(@PathVariable(value = "id") int id, @RequestBody InvoiceItemDTO invoice) {
        InvoiceItem newInvoice = invoiceService.updateInvoiceItem(id, invoice);
        LOGGER.info("update item quantity from controller with item id : " + id);
        return new ResponseEntity<InvoiceItem>(newInvoice, HttpStatus.OK);
    }



}
