package com.example.invoices.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }
    
    
    
    @GetMapping("/getCustomersByPageLimit")
    public List<Customer> getListOfCustomers(){
    	
    	
    	return null;
    }
    
    @PostMapping("/save")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		//Customer customer = mapper.fromCustomerDTO(customerDto);
		Customer customer1 = customerService.addCustomer(customer);
		if (customer1.getId() > 0)
			return new ResponseEntity<Customer>(customer1, HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Customer is not added", HttpStatus.METHOD_FAILURE);
	}
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {

		Customer customer1 = customerService.updateCustomer(customer);

		return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
	}
	
	
	@PatchMapping("/updateCustomerInvoice")
	public ResponseEntity<Customer> updateCustomerInvoice(@RequestBody Customer customer) {
		Set<Invoice> updatedList = new HashSet<>();
		
        for(Invoice invoince: customer.getInvoices()) {
		
		Invoice updatedInvoince = customerService.updateCustomerInvoice(invoince,  customer.getId());
		updatedList.add(updatedInvoince);
        }
        customer.setInvoices(updatedList);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	 
	
	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable int customerId) {

		Customer customer1 = customerService.getCustomer(customerId);
		return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(int customerId) {

		if (customerId > 0) {
			Customer customer1 = new Customer();
			int deleteStatus = customerService.deleteCustomer(customer1);
			if (deleteStatus >= 1) {
				return new ResponseEntity<String>("Customer deleted succeessfully.", HttpStatus.OK);
			}
		} else {
			//throw new EmptyInputParam("Customer id not found", 400);
		}

		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}
