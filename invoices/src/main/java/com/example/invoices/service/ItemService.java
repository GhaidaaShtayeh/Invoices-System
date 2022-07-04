package com.example.invoices.service;

import com.example.invoices.dto.ItemDTO;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.Item;

import java.util.List;

public interface ItemService {
    public Item saveItem(ItemDTO item);
    public List<Item> getAllItem();

    Item getItemBySerialNumber(long serialNumber);

    public Item updateItem(Item item);
    public Item deleteItem(int itemId);
}
