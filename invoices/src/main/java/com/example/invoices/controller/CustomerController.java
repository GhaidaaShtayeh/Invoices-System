package com.example.invoices.controller;
import java.util.*;

import com.example.invoices.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.service.CustomerServiceImpl;
import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("/viewList")
	@CrossOrigin("http://localhost:4200/")
	public ResponseEntity<List<Customer>> getAllCustomers() {
			List<Customer> customers = new ArrayList<>();
			customers = customerService.getAllCustomers();
			return new ResponseEntity<>(customers, HttpStatus.OK);
	}

    @PostMapping("/save")
	@CrossOrigin("http://localhost:4200/")
		public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customer) {
		try {
				Customer newCustomer = customerService.addCustomer(customer);
				return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") int id, @RequestBody CustomerDTO customer) {
		try{
			Customer customer1 = customerService.updateCustomer(id, customer);
			LOGGER.info(" update work successfully ");
			return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
		}catch (Exception exception){
			LOGGER.error("Exception in update method " + exception.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable @RequestBody int customerId) {
			Optional<Customer> customerData = Optional.ofNullable(customerService.getCustomer(customerId));
			if (customerData.isPresent()) {
				LOGGER.info(" show customer work successfully ");
				return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
			} else {
				LOGGER.info(" exception in getCustomer  ");
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
