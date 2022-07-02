package com.example.invoices.service;

import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;
import com.example.invoices.model.Item;
import com.example.invoices.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item saveItem(Item item) {
        Item newItem = itemRepository.save(item);
        return newItem;
    }

    @Override
    public List<Item> getAllItem() {
        List<Item> items =  itemRepository.getAllItems();
        return items;
    }

    @Override
    public Item updateItem(Item item) {
        return null;
    }

    @Override
    public Item deleteItem(int itemId) {
        return null;
    }
}
