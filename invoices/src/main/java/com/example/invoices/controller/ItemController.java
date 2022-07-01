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

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/viewList")
    @CrossOrigin("http://localhost:4200/")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = new ArrayList<>();
        items = itemService.getAllItem();
        if (items.isEmpty()) {
            LOGGER.error("no content ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        LOGGER.info("calling list of customers");
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @PostMapping("/save")
    @CrossOrigin("http://localhost:4200/")
    public ResponseEntity<Item> addItem(@RequestBody ItemDTO item) {
        try {
            Item newCustomer = itemService
                    .saveItem(new Item(item.getSerialNumber(), item.getName(),item.getUnitPrice()));
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Exception in save method " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
