package com.example.invoices.service;

import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.ICutomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	ICutomerRepository cutomerRepository;

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		cutomerRepository.findAll().forEach(customers1 -> customers.add(customers1));
		for (Customer cus : customers) {
			System.out.println("***************************");
			System.out.println(cus.getName());
		}
		return customers;
	}

	@Override
	@Transactional
	public Customer addCustomer(Customer customer) {
		return cutomerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(int customerId) {
		System.out.println("************************Id:" + customerId);
		return cutomerRepository.findById(customerId).get();
	}

	@Override
	@Transactional
	public Customer updateCustomer(Customer customer) {
		if (cutomerRepository.findById(customer.getId()) != null) {
			// System.out.println("Id:"+book.getId());
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
