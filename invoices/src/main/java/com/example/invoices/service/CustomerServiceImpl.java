package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.exception.EmptyListException;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository cutomerRepository;


	public List<Customer> getAllCustomers() {
		List<Customer> customers =  cutomerRepository.getAllCustomers();
		if (customers.isEmpty()) {
			LOGGER.error("no content in customers ");
			throw new EmptyListException("customers list are empty");
		}
		LOGGER.info("calling list of customers");
		return customers;
	}

	@Override
	@Transactional
	public Customer addCustomer(CustomerDTO customer) {
		Customer newCustomer = null;
			newCustomer = new Customer(customer.getSerialNumber(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getMobileNumber());
			newCustomer = cutomerRepository.save(newCustomer);
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

}
