package com.example.invoices.service;

import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements  InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository ;

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
        Date updatedDate = new Date();
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        invoice.setStatus(invoiceDetails.getStatus());
        invoice.setCreatedDate(new Timestamp(updatedDate.getTime()));
        invoice.setSerialNumber(invoiceDetails.getSerialNumber());
        return invoiceRepository.save(invoice);
    }

    @Override
    public boolean deleteInvoice(int invoiceId) {
        try{
            Invoice invoice = invoiceRepository.findById(invoiceId).get();
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
            return true;
        }catch(Exception exception){
            return false;
        }

    }
    @Override
    public Invoice getInvoice(long serialNumber) {
        Invoice invoice = invoiceRepository.findBySerialNumber(serialNumber);
        return invoice;
    }
}
