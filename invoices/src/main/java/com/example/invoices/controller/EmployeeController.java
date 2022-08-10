package com.example.invoices.controller;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.dto.InvoiceDTO;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.model.Role;
import com.example.invoices.repository.RoleRepository;
import com.example.invoices.utilite.SetHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.example.invoices.model.Employee;
import com.example.invoices.service.EmployeeService;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")

/*
here I put all employee controllers (employee refer to user in the system)
I can add new employee and show list of employees in super_user page, update them
and show details
 */
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;


	/*
	* it's the register api and use in add new employee  in super user page
	*/
	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employee) {
		Employee employee1 = employeeService.saveEmployee(employee);
		LOGGER.info(" new employee registered into system with serial number  : " + employee.getSerialNumber() + " this calling from controller");
		return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
	}


	/*
	* display list of users that are registered in the system
	*/
	@GetMapping("/viewList")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> customers = employeeService.getAllEmployee();
		LOGGER.info(" get all customers controllers are calling ");
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	/*
	* update employee details in the system
	*/
	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") int id, @RequestBody EmployeeDTO employeeDTO) {
		Employee newEmployee = employeeService.updateEmployee(id, employeeDTO);
		LOGGER.info(" history added into invoice " + employeeDTO.getSerialNumber() + " serial from controller");
		LOGGER.info(" invoice id : "+id+" updated from controller");
		return new ResponseEntity<Employee>(newEmployee, HttpStatus.OK);
	}

	/*
	show and display employee details
	*/
	@GetMapping("/get-employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable int id){
		Optional<Employee> employee = employeeService.getEmployee(id);
		LOGGER.info(" get employee with id " + id +" details Api are calling from controller");
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
