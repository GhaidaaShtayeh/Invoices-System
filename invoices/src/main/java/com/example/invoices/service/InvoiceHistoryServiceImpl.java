package com.example.invoices.service;

import com.example.invoices.dto.InvoiceHistoryDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.InvoiceHistory;
import com.example.invoices.repository.InvoiceHistoryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Data
@Service
public class InvoiceHistoryServiceImpl implements InvoiceHistoryService {
    @Autowired
    InvoiceHistoryRepository invoiceHistoryRepository;

    @Override
    public InvoiceHistory saveInvoiceHistory(InvoiceHistoryDTO invoiceHistoryDTO) {
        try{
            InvoiceHistory invoiceHistory = new InvoiceHistory(invoiceHistoryDTO.getUpdatedDate(), invoiceHistoryDTO.getInvoiceInfo(), invoiceHistoryDTO.getInvoiceInfo().toString(), invoiceHistoryDTO.getInvoiceInfo().getEmployee());
            InvoiceHistory newInvoiceHistory = invoiceHistoryRepository.save(invoiceHistory);
            return newInvoiceHistory;
        }catch (Exception exception){
            LOGGER.error("error while saving history in " + invoiceHistoryDTO.getUpdatedDate());
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            return null;
        }

    }

    @Override
    public List<InvoiceHistory> getInvoice(long serialNumber) {
        try{
            List<InvoiceHistory> invoice = invoiceHistoryRepository.findAllByInvoiceSerialNumber(serialNumber);
            return invoice;
        }catch (Exception exception){
            LOGGER.error("error while getting history in " + serialNumber);
            LOGGER.error("exception message " + exception.getMessage());
            LOGGER.error(exception.getStackTrace());
            return null;
        }

    }

}
