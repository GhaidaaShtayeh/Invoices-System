package com.example.invoices.controller;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.model.Role;
import com.example.invoices.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.example.invoices.model.Employee;
import com.example.invoices.service.EmployeeService;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	RoleRepository roleRepository;

	@PostMapping("/save")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employee) {
		Employee employee1 = employeeService.saveEmployee(employee);
		LOGGER.info(" new employee registered into system with serial number  : " + employee.getSerialNumber() + " this calling from controller");
		return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);

	}

}
