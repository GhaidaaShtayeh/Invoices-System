package com.example.invoices.controller;
import java.util.*;

import com.example.invoices.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.service.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("/viewList")
	public ResponseEntity<List<Customer>> getAllCustomers() {
			List<Customer> customers = new ArrayList<>();
			customers = customerService.getAllCustomers();
			if (customers.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(customers, HttpStatus.OK);
	}

    @GetMapping("/getCustomersByPageLimit")
    public List<Customer> getListOfCustomers(){

    	return null;
    }

    @PostMapping("/save")
		public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customer) {
			try {
				Customer newCustomer = customerService
						.addCustomer(new Customer(customer.getSerialNumber(), customer.getFirstName(),customer.getLastName(),customer.getEmail(),customer.getMobileNumber()));
				return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	@PatchMapping("/updateCustomerInvoice")
	public ResponseEntity<CustomerDTO> updateCustomerInvoice(@RequestBody CustomerDTO customer) {
		Set<Invoice> updatedList = new HashSet<>();
		
        for(Invoice invoice: customer.getInvoices()) {
		
		Invoice updatedInvoice = customerService.updateCustomerInvoice(invoice,  customer.getId());
		updatedList.add(updatedInvoice);
        }
        customer.setInvoices(updatedList);
		return new ResponseEntity<CustomerDTO>(customer, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") int id, @RequestBody CustomerDTO customer) {

		Customer customer1 = customerService.updateCustomer(id, customer);

		return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable @RequestBody int customerId) {
			Optional<Customer> customerData = Optional.ofNullable(customerService.getCustomer(customerId));
			if (customerData.isPresent()) {
				return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
	@PutMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {
		if (customerId > 0) {
			boolean deleteStatus = customerService.deleteCustomer(customerId);
			if (deleteStatus) {
				return new ResponseEntity<String>("Customer deleted succeessfully.", HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<String>("Customer not deleted .", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
