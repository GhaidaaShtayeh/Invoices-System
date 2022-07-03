package com.example.invoices.service;

import java.util.Optional;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.model.Role;
import com.example.invoices.repository.EmployeeRepository;
import com.example.invoices.repository.RoleRepository;
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

	@Autowired
	RoleRepository roleRepository;
	@Override
	public Employee saveEmployee(EmployeeDTO employee) {
		Role role = roleRepository.findById(employee.getRoleId());
		Employee newEmployee = new Employee(employee.getSerialNumber(),employee.getFirstName(), employee.getLastName(),role,employee.getEmail(),employee.getMobileNumber(), employee.getCountry(), employee.getPassword());
		String encodedPassword = passwordEncoder.encode(newEmployee.getPassword());
		newEmployee.setPassword(encodedPassword);
		return employeeRepository.save(newEmployee);
	}

	@Override
	public Employee getEmployee(Employee employee) {
		
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		emp.orElseThrow(() -> new UsernameNotFoundException("User not found."));
		return emp.get();
	}



}
