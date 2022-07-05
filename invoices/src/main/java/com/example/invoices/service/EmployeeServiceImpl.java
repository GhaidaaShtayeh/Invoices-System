package com.example.invoices.service;

import java.util.Optional;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.exception.EmployeeNotFoundException;
import com.example.invoices.exception.InvoiceNotFoundException;
import com.example.invoices.model.Role;
import com.example.invoices.repository.EmployeeRepository;
import com.example.invoices.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.invoices.model.Employee;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

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
		try{
			Role role = roleRepository.findById(employee.getRoleId());
			Employee newEmployee = new Employee(employee.getSerialNumber(),employee.getFirstName(), employee.getLastName(),role,employee.getEmail(),employee.getMobileNumber(), employee.getCountry(), employee.getPassword());
			String encodedPassword = passwordEncoder.encode(newEmployee.getPassword());
			newEmployee.setPassword(encodedPassword);
			LOGGER.info("save new customer with id " + newEmployee.getSerialNumber() + " from service");
			return employeeRepository.save(newEmployee);
		} catch (Exception exception){
			LOGGER.error("error while saving user with id " + employee.getSerialNumber());
			LOGGER.error("exception message " + exception.getMessage());
			LOGGER.error(exception.getStackTrace());
			return employeeRepository.findBySerialNumber(employee.getSerialNumber());
		}

	}

	@Override
	public Employee getEmployee(Employee employee) {

		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		emp.orElseThrow(() -> new UsernameNotFoundException("User not found."));
		return emp.get();
	}

	@Override
	public Employee getEmployeeBySerialNumber(long serialNumber){
		Employee employee = employeeRepository.findBySerialNumber(serialNumber);
		if(!(employeeRepository.existsById(employee.getId()))){
			throw new EmployeeNotFoundException();
		}
		try{
			LOGGER.info("get employee with id " + serialNumber + " from service");
			return employee;
		}catch (Exception exception){
			LOGGER.error("error while saving user with id " + serialNumber);
			LOGGER.error("exception message " + exception.getMessage());
			LOGGER.error(exception.getStackTrace());
			return employeeRepository.findBySerialNumber(serialNumber);
		}
	}



}
