package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.IInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InvoiceServiceImpl implements  InvoiceService {
    @Autowired
    IInvoiceRepository invoiceRepository ;

    @Override
    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        Invoice newInvoice = invoiceRepository.save(invoice);
        return newInvoice;
    }

    @Override
    public List<Invoice>  getInvoice() {
        List<Invoice> customers =  invoiceRepository.findAll();
        return customers;
    }

    @Override
    public Invoice updateInvoice(int invoiceId, InvoiceDTO invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        invoice.setStatus(invoiceDetails.getStatus());
        invoice.setCreatedDate(invoiceDetails.getCreatedDate());
        invoice.setSerialNumber(invoiceDetails.getSerialNumber());
        return invoiceRepository.save(invoice);
    }

    @Override
    public boolean deleteInvoice(int invoiceId) {
        try{
            Invoice customer = invoiceRepository.findById(invoiceId).get();
            customer.setDeleted(true);
            invoiceRepository.save(customer);
            return true;
        }catch(Exception exception){
            return false;
        }

    }
}
