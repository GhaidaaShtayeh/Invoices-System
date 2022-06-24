package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

public interface CustomerService {

	public Customer addCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public Invoice updateCustomerInvoice(Invoice invoince, int id);

	public Customer updateCustomer(int customerId, CustomerDTO customerDetails);

	public boolean deleteCustomer(int customerId);
}
