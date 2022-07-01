package com.example.invoices.service;

import com.example.invoices.model.Invoice;
import com.example.invoices.model.Item;

import java.util.List;

public interface ItemService {
    public Item saveItem(Item item);
    public List<Item> getAllItem();
    public Item updateItem(Item item);
    public Item deleteItem(int itemId);
}
