package com.example.invoices.service;

import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.exception.InvoiceAlreadyExistsException;
import com.example.invoices.exception.InvoiceIsDeletedException;
import com.example.invoices.exception.InvoiceNotFoundException;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.CustomerRepository;
import com.example.invoices.repository.EmployeeRepository;
import com.example.invoices.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

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
        Invoice newInvoice = new Invoice(invoice.getSerialNumber(), invoice.getStatus(),new Timestamp(updatedDate.getTime()) ,employee,customer , invoice.getPhoto());
        if(invoiceRepository.existsById(invoice.getId())){
            LOGGER.error("error while saving invoices " + invoice.getSerialNumber() +" already Exists ");
            throw new InvoiceAlreadyExistsException();
        }
         newInvoice = invoiceRepository.save(newInvoice);
        return newInvoice;
    }

    @Override
    public List<Invoice>  getInvoice() {
        try {
            List<Invoice> invoices =  invoiceRepository.getAllInvoices();
            return invoices;
        }catch (Exception exception){
            LOGGER.error("error while getting invoices ");
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            return null ;
        }

    }
    @Override
    public Invoice  getInvoiceBySerialNumber(long serialNumber) {
        Invoice invoice =  invoiceRepository.findBySerialNumber(serialNumber);
        if(!(invoiceRepository.existsById(invoice.getId()))){
            LOGGER.error("error while getting invoice " + serialNumber +" not Exists ");
            throw new InvoiceNotFoundException();
        }
        return invoice;
    }

    public List<Invoice> findAllInvoices(Pageable pageable){
        List<Invoice> invoices =  invoiceRepository.findAll(pageable).toList();
        return invoices;
    }

    @Override
    public Invoice updateInvoice(int invoiceId, InvoiceDTO invoiceDetails) {
        if(!(invoiceRepository.existsById(invoiceId))){
            throw new InvoiceNotFoundException();
        }
        Date updatedDate = new Date();
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
        try {
            invoice.setStatus(invoiceDetails.getStatus());
            invoice.setCreatedDate(new Timestamp(updatedDate.getTime()));
            invoice.setSerialNumber(invoiceDetails.getSerialNumber());
            return invoiceRepository.save(invoice);
        }catch(Exception exception){
            LOGGER.error("error while update invoice ");
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            return invoiceRepository.findBySerialNumber(invoice.getSerialNumber());
        }

    }

    @Override
    public boolean deleteInvoice(int invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).get();
            if(invoice.isDeleted()){
                LOGGER.error("error while delete invoice invoice already deleted with id " + invoiceId);
                throw new InvoiceIsDeletedException("error while delete invoice invoice already deleted");
            }
            else {
            invoice.setDeleted(true);
            invoiceRepository.save(invoice);
            return true;
            }

    }
    @Override
    public Invoice getInvoice(long serialNumber) {
        if(invoiceRepository.findBySerialNumber(serialNumber)==null){
            throw new InvoiceNotFoundException();
        }
        else {
            Invoice invoice = invoiceRepository.findBySerialNumber(serialNumber);
            return invoice;
        }

    }

    @Override
    public List<Invoice> getAllInvoicesByEmpId(Employee employee) {
        try{
            List<Invoice> invoices = invoiceRepository.findByEmployee(employee);
            return invoices;
        } catch(Exception exception){
            LOGGER.error("error while getting invoices ");
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            return null ;
        }

    }
}
