package com.example.invoices.service;

import com.example.invoices.dto.CustomerDTO;
import com.example.invoices.exception.CustomerIsDeletedException;
import com.example.invoices.exception.EmptyListException;
import com.example.invoices.exception.ItemIsDeletedException;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
		try{
			Customer newCustomer = new Customer(customer.getSerialNumber(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getMobileNumber());
			newCustomer = cutomerRepository.save(newCustomer);
			LOGGER.info("save new customer with id " + customer.getSerialNumber() + " from service");
			return newCustomer;
		}catch (Exception exception){
			LOGGER.error("exception catching while add new customer from service" +  exception.getMessage());
			LOGGER.error(exception.getStackTrace());
			return cutomerRepository.findBySerialNumber(customer.getSerialNumber());
		}


	}

	@Override
	public Customer getCustomer(int customerId) {
		Customer customer = null;
		try{
			customer =  cutomerRepository.findById(customerId).get();
			LOGGER.info("getting customer with id : " + customerId + " from service");
			return  customer;
		}catch(Exception exception){
			LOGGER.error("error while getting customer with id " + customerId);
			LOGGER.error("exception message " + exception.getMessage());
			LOGGER.error(exception.getStackTrace());
			return null;
		}
	}

	@Override
	@Transactional
	public Customer updateCustomer(int customerId, CustomerDTO customerDetails) {
		try {
			Customer customer = cutomerRepository.findById(customerId).get();
			customer.setFirstName(customerDetails.getFirstName());
			customer.setLastName(customerDetails.getLastName());
			customer.setEmail(customerDetails.getEmail());
			customer.setMobileNumber(customerDetails.getMobileNumber());
			customer.getSerialNumber(customerDetails.getSerialNumber());
			LOGGER.info("getting customer with id updated : " + customerId + " from service");
			return cutomerRepository.save(customer);
		}catch (Exception exception){
			LOGGER.error("error while updating customer with id " + customerId);
			LOGGER.error("exception message " + exception.getMessage());
			LOGGER.error(exception.getStackTrace());
			return null;
		}

	}

	@Override
	public boolean deleteCustomer(int customerId) {
		Customer customer = cutomerRepository.findById(customerId).get();
		if(customer.isDeleted()){
			LOGGER.error("error while deleting customer with serial number" + customerId);
			throw new CustomerIsDeletedException("customer is already deleted");
		}
		try {
			customer.setDeleted(true);
			LOGGER.info("getting customer with id deleted : " + customerId + " from service");
			cutomerRepository.save(customer);
			return true;
		} catch (Exception e) {
			LOGGER.error("error while deleting customer with id " + customerId);
			LOGGER.error("exception message " + e.getMessage());
			LOGGER.error(e.getStackTrace());
			return false;
		}
	}

}
