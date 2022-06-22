package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

	public Customer addCustomer(Customer customer);

	public Customer getCustomer(int id);

	public Customer updateCustomer(CustomerDTO customer);

	public Invoice updateCustomerInvoice(Invoice invoince, int id);

	public int deleteCustomer(Customer customer);
}
