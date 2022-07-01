package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository cutomerRepository;


	public List<Customer> getAllCustomers() {
		List<Customer> customers =  cutomerRepository.findAllByIsDeletedIsFalse();
		return customers;
	}

	@Override
	@Transactional
	public Customer addCustomer(Object customer) {
		Customer newCustomer = cutomerRepository.save((Customer) customer);
		return newCustomer;
	}

	@Override
	public Customer getCustomer(int customerId) {
		return cutomerRepository.findById(customerId).get();
	}

	@Override
	@Transactional
	public Customer updateCustomer(int customerId, CustomerDTO customerDetails) {
		Customer customer = cutomerRepository.findById(customerId).get();
		customer.setFirstName(customerDetails.getFirstName());
		customer.setLastName(customerDetails.getLastName());
		customer.setEmail(customerDetails.getEmail());
		customer.setMobileNumber(customerDetails.getMobileNumber());
		customer.getSerialNumber(customerDetails.getSerialNumber());

		return cutomerRepository.save(customer);
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		try {
			Customer customer = cutomerRepository.findById(customerId).get();
			customer.setDeleted(true);
			cutomerRepository.save(customer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public Invoice updateCustomerInvoice(Invoice invoice, int id) {
		// cutomerRepository.updatePrice(invoice, id);

		return invoice;
	}
}
