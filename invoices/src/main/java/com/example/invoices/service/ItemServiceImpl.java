package com.example.invoices.service;

import com.example.invoices.dto.ItemDTO;
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
    public Item saveItem(ItemDTO item) {
        Item newItem = new Item(item.getSerialNumber(), item.getName(),item.getUnitPrice());
         Item newItem2 = itemRepository.save(newItem);
        return newItem2;
    }

    @Override
    public List<Item> getAllItem() {
        List<Item> items =  itemRepository.getAllItems();
        return items;
    }
    @Override
    public Item getItemBySerialNumber(long serialNumber){
        Item item = itemRepository.findBySerialNumber(serialNumber);
        return item;
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
