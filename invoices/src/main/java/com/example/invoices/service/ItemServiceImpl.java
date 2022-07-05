package com.example.invoices.service;

import com.example.invoices.dto.ItemDTO;
import com.example.invoices.exception.*;
import com.example.invoices.model.Item;
import com.example.invoices.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;


    @Override
    public Item saveItem(ItemDTO item) {
        Item newItem = new Item(item.getSerialNumber(), item.getName(),item.getUnitPrice());
        if(itemRepository.existsBySerialNumber(item.getSerialNumber())){
            LOGGER.error("try to add item with copied serial number " + item.getSerialNumber());
            throw new ItemAlreadyExistsException();
        }
        try{
            Item newItem2 = itemRepository.save(newItem);
            return newItem2;
        }catch(Exception exception){
            LOGGER.error("error while save item ");
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            return null ;
        }
    }

    @Override
    public List<Item> getAllItem() {
        try {
            List<Item> items = itemRepository.getAllItems();
            return items;
        }catch (EmptyListException emptyListException){

            LOGGER.error("error while getting item ");
            throw new EmptyListException("empty list of items are getting");

        }catch (Exception exception){
            LOGGER.error("error while getting item ");
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            return null ;
        }

    }
    @Override
    public Item getItemBySerialNumber(long serialNumber){
        if(itemRepository.findBySerialNumber(serialNumber)==null){
            LOGGER.error("error while getting item with serial number" + serialNumber);
            throw new ItemNotFoundException();
        }
        Item item = itemRepository.findBySerialNumber(serialNumber);
        return item;
    }


    @Override
    public boolean deleteItem(int itemId) {
        Item item = itemRepository.findById(itemId).get();
        if(item.isDeleted()){
            LOGGER.error("error while deleting item with serial number" + itemId);
            throw new ItemIsDeletedException("item is already deleted");
        }
        else {
            item.setDeleted(true);
            itemRepository.save(item);
            return true;
        }
    }
}
