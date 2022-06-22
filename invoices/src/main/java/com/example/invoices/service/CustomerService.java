package com.example.invoices.service;

import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;

public interface CustomerService {

	public Customer addCustomer(Customer customer);

	public Customer getCustomer(int id);

	public Customer updateCustomer(Customer customer);

	public Invoice updateCustomerInvoice(Invoice invoince, int id);

	public int deleteCustomer(Customer customer);
}
