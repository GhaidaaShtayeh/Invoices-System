package com.example.invoices.service;

import com.example.invoices.model.Invoice;
import com.example.invoices.model.Item;

public interface ItemService {
    public Item saveItem(Item item);
    public Item getItem(Item item);
    public Item updateItem(Item item);
    public Item deleteItem(int itemId);
}
