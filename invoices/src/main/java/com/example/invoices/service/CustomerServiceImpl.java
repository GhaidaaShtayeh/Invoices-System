package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.ICutomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	ICutomerRepository cutomerRepository;
	public List<CustomerDTO> getAllCustomers() {
		List<CustomerDTO> customers = Collections.singletonList((CustomerDTO) cutomerRepository.findAll());
		return customers;
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		Customer newCustomer = cutomerRepository.save(customer);
		return newCustomer;
	}

	@Override
	public Customer getCustomer(int customerId) {
		System.out.println("************************Id:" + customerId);
		return cutomerRepository.findById(customerId).get();
	}

	@Override
	@Transactional
	public Customer updateCustomer(CustomerDTO customer) {
		if (cutomerRepository.findById(customer.getId()) != null) {
			Customer persistenceCustomer = cutomerRepository.findById(customer.getId()).get();
			if (customer.getEmail() != null) {
				persistenceCustomer.setEmail(customer.getEmail());
				;
			}
			if (customer.getMobileNumber() != null) {
				persistenceCustomer.setMobileNumber(customer.getMobileNumber());
			}
			if (customer.getInvoices().size() > 0) {
				persistenceCustomer.setInvoices(customer.getInvoices());
			}

			return cutomerRepository.save(persistenceCustomer);
		}
		return null;
	}

	@Override
	public int deleteCustomer(Customer customer) {
		try {
			cutomerRepository.delete(customer);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public Invoice updateCustomerInvoice(Invoice invoice, int id) {
		// cutomerRepository.updatePrice(invoice, id);

		return invoice;
	}
}
