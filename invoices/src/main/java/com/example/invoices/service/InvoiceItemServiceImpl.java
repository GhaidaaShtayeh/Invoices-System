package com.example.invoices.service;

import com.example.invoices.dto.InvoiceItemDTO;
import com.example.invoices.exception.EmptyListException;
import com.example.invoices.exception.InvoiceAlreadyExistsException;
import com.example.invoices.exception.InvoiceNotFoundException;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceItem;
import com.example.invoices.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service

public class InvoiceItemServiceImpl implements  InvoiceItemService{
    @Autowired
    InvoiceItemRepository invoiceRepository ;


    @Override
    public InvoiceItem saveInvoiceItem(InvoiceItem invoice) {
        try{
            InvoiceItem newInvoice = invoiceRepository.save(invoice);
            return newInvoice;
        }catch (Exception exception){
            throw new InvoiceAlreadyExistsException();
        }

    }

    @Override
    public List<InvoiceItem> getAllInvoiceItem(long serialNumber) {
        try { List<InvoiceItem> invoices = invoiceRepository.findAllByInvoiceSerialNumber(serialNumber);
         return invoices;
     } catch (Exception e){
    throw new EmptyListException();
        }
    }

    @Override
    public InvoiceItem updateInvoiceItem(@PathVariable(value = "id") int invoiceId, @RequestBody InvoiceItemDTO invoice) {
        try { InvoiceItem newInvoice = invoiceRepository.findById(invoiceId).get();
     newInvoice.setQuantity(invoice.getQuantity());
     return invoiceRepository.save(newInvoice);
        } catch (Exception exception){
     throw new InvoiceNotFoundException();
        }


    }



   /* @Override
    public boolean deleteInvoiceItem(int invoiceId) {

        try{
            InvoiceItem invoiceItem = invoiceRepository.findById(invoiceId).get();
            invoiceItem.setDeleted(true);
            invoiceRepository.save(invoiceItem);
            return true;
        }catch(Exception exception){
            return false;
        }
    }*/
}
