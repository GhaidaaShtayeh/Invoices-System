package com.example.invoices.service;

import com.example.invoices.dto.InvoiceItemDTO;
import com.example.invoices.exception.EmptyListException;
import com.example.invoices.exception.InvoiceAlreadyExistsException;
import com.example.invoices.exception.InvoiceNotFoundException;
import com.example.invoices.model.InvoiceItem;
import com.example.invoices.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service

public class InvoiceItemServiceImpl implements  InvoiceItemService{
    @Autowired
    InvoiceItemRepository invoiceRepository ;


    @Override
    public InvoiceItem saveInvoiceItem(InvoiceItem invoice) {
        try{
            InvoiceItem newInvoice = invoiceRepository.save(invoice);
            LOGGER.info("save Invoice item ");
            return newInvoice;
        }catch (Exception exception){
            throw new InvoiceAlreadyExistsException();
        }

    }

    @Override
    public List<InvoiceItem> getAllInvoiceItem(long serialNumber) {
        List<InvoiceItem> invoices = invoiceRepository.findAllByInvoiceSerialNumber(serialNumber);
            if(invoices ==null){
                throw new EmptyListException("Empty list of invoices are calling");
            }
        try {
            LOGGER.info("Invoice Item getting for invoice  " + serialNumber + " from service");
            return invoices;
     } catch (Exception exception){
            LOGGER.error("error while getting history in " + serialNumber);
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
             throw new EmptyListException();
        }
    }

    @Override
    public InvoiceItem updateInvoiceItem(@PathVariable(value = "id") int invoiceId, @RequestBody InvoiceItemDTO invoice) {
        try {
            InvoiceItem newInvoice = invoiceRepository.findById(invoiceId).get();
         newInvoice.setQuantity(invoice.getQuantity());
            LOGGER.info("Invoice Item quantity getting updated for invoice  " + invoiceId + " from service");
            return invoiceRepository.save(newInvoice);
        } catch (Exception exception){
            LOGGER.error("error while getting history in " + invoiceId);
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            throw new InvoiceNotFoundException();
        }


    }

}
