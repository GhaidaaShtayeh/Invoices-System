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

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	RoleRepository roleRepository;

	@PostMapping("/save")
	@CrossOrigin("http://localhost:4200/")

	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDTO employee) {
		Role role = roleRepository.findById(employee.getRoleId());
		Employee newEmployee = new Employee(employee.getSerialNumber(),employee.getFirstName(), employee.getLastName(),role,employee.getEmail(),employee.getMobileNumber(), employee.getCountry(), employee.getPassword());
		Employee employee1 = employeeService.saveEmployee(newEmployee);
		if (employee1.getId() > 0){
			LOGGER.info(" new user registered ");
			return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
		}
		else{
			LOGGER.info(" sno user added ");
			return new ResponseEntity<String>("Employee is not added", HttpStatus.METHOD_FAILURE);
		}
	}

}
