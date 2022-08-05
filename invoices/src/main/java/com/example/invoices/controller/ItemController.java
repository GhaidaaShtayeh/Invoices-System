package com.example.invoices.controller;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.dto.ItemDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Item;
import com.example.invoices.repository.ItemRepository;
import com.example.invoices.service.CustomerServiceImpl;
import com.example.invoices.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/viewList")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItem();
        LOGGER.info("calling list of Items from controller ");
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<Item> addItem(@RequestBody ItemDTO item) {
            Item newCustomer = itemService.saveItem(item);
        LOGGER.info("calling add new items from controller ");
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/deleteItem/{itemSerialNumber}")
    public boolean deleteInvoice(@PathVariable long itemSerialNumber) {
        boolean deleteStatus = itemService.deleteItem(itemSerialNumber);
        LOGGER.info(" invoice deleted " + itemSerialNumber + " id ");
        return true;
    }
}
