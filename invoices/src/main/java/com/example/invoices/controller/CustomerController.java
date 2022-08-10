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
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer")
/*
* Here  I put all controllers needed for customer table which is access by super_user only, u can add
* edit delete customers, and u can get specific customer data in order to show details
* */
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

	/*
	* get all customers that are registered in the system */
    @GetMapping("/viewList")
	public ResponseEntity<List<Customer>> getAllCustomers() {
			List<Customer> customers = customerService.getAllCustomers();
		LOGGER.info(" get all customers controllers are calling ");
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	/*
	* add new customer to the system */
    @PostMapping("/save")
		public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customer) {
				Customer newCustomer = customerService.addCustomer(customer);
		LOGGER.info("add new customer are calling from controller with id " + newCustomer.getSerialNumber() );
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
		}


		/*
		update and edit customer details
		*/
	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") int id, @RequestBody CustomerDTO customer) {
			Customer newCustomer = customerService.updateCustomer(id, customer);
		LOGGER.info("update customer are calling from controller with id " + newCustomer.getSerialNumber() );
		return new ResponseEntity<Customer>(newCustomer, HttpStatus.OK);

	}

	/*
	* get customer details and show them in the system
	* */
	@GetMapping("/getCustomer/{customerId}")
	public ResponseEntity<?> getCustomer(@PathVariable @RequestBody int customerId) {
			Optional<Customer> customerData = Optional.ofNullable(customerService.getCustomer(customerId));
		LOGGER.info("get customer details are calling from controller for customer id :  " + customerId );
		return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
		}

		/*
		* delete the customer (soft delete) by change -is_deleted column in
		* database to true and then not display it
		* */
	@GetMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) {
			boolean deleteStatus = customerService.deleteCustomer(customerId);
		LOGGER.info("delete customer calling from controller for customer id :  " + customerId );
		return new ResponseEntity<String>("Customer deleted.", HttpStatus.OK);

	}
}
