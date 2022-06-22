package com.example.invoices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.invoices.model.Employee;
import com.example.invoices.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	 PasswordEncoder passwordEncoder;
	
	@Autowired
	IEmployeeRepository employeeRepository;
	
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

	@Override
	public Employee updateEmployee(Employee employee) {
		
		return null;
	}

	@Override
	public Employee deleteEmployee(int empId) {
		try {
			Optional<Employee> empObj = employeeRepository.findById(empId);
			empObj.orElseThrow(() -> new UsernameNotFoundException("Employee not found."));
			
			if(empObj.isPresent()) {
				employeeRepository.delete(empObj.get());
			}
			return empObj.get() ;
			
		} catch (Exception e) {
			
			return null;
		}
	}

}
