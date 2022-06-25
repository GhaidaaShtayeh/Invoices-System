package com.example.invoices.service;

import java.util.Optional;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.invoices.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	 PasswordEncoder passwordEncoder;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Employee saveEmployee(Employee employee) {
		    String encodedPassword = passwordEncoder.encode(employee.getPassword());
	        employee.setPassword(encodedPassword);
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployee(Employee employee) {
		
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		emp.orElseThrow(() -> new UsernameNotFoundException("User not found."));
		return emp.get();
	}



}
