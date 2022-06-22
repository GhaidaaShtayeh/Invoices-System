package com.example.invoices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoices.model.Employee;
import com.example.invoices.service.EmployeeService;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@RolesAllowed({ "USER" })
	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {

		Employee employee1 = employeeService.saveEmployee(employee);
		if (employee1.getId() > 0)
			return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Employee is not added", HttpStatus.METHOD_FAILURE);
	}
	
	
	
	
	
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<?> deleteEmployee(int employeeId) {

		if (employeeId > 0) {
			Employee deletedObject = employeeService.deleteEmployee(employeeId);

			return new ResponseEntity<Employee>(deletedObject, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<String>("Employee is not Deleted", HttpStatus.METHOD_FAILURE);
		}
	}
}
