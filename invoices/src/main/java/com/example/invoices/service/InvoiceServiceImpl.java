package com.example.invoices.service;

import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.exception.InvoiceAlreadyExistsException;
import com.example.invoices.exception.InvoiceNotFoundException;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.CustomerRepository;
import com.example.invoices.repository.EmployeeRepository;
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
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    @Transactional
    public Invoice saveInvoice(InvoiceDTO invoice) {
        Customer customer= customerRepository.findBySerialNumber(invoice.getCustomerSerialNumber());
        Employee employee = employeeRepository.findBySerialNumber(invoice.getEmployeeSerialNumber());
        Date updatedDate = new Date();
        Invoice newInvoice = new Invoice(invoice.getSerialNumber(), invoice.getStatus(),new Timestamp(updatedDate.getTime()) ,employee,customer);
        if(invoiceRepository.existsById(invoice.getId())){
            throw new InvoiceAlreadyExistsException();
        }
         newInvoice = invoiceRepository.save(newInvoice);
        return newInvoice;
    }

    @Override
    public List<Invoice>  getInvoice() {
        List<Invoice> invoices =  invoiceRepository.getAllInvoices();
        return invoices;
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
        if(invoiceRepository.findBySerialNumber(serialNumber)==null){
            throw new InvoiceNotFoundException();
        }
        else{
            Invoice invoice = invoiceRepository.findBySerialNumber(serialNumber);
            return invoice;
        }

    }

    @Override
    public List<Invoice> getAllInvoicesByEmpId(Employee employee) {
        List<Invoice> invoices = invoiceRepository.findByEmployee(employee);
        return invoices;
    }
}
