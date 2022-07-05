package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;

public interface CustomerService {

	public Customer addCustomer(CustomerDTO customer);

	public Customer getCustomer(int customerId);

	public Customer updateCustomer(int customerId, CustomerDTO customerDetails);

	public boolean deleteCustomer(int customerId);
}
