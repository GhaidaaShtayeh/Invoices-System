package com.example.invoices.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.exception.EmployeeNotFoundException;
import com.example.invoices.exception.EmptyListException;
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
	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> Employees =  employeeRepository.findAll();
		if (Employees.isEmpty()) {
			LOGGER.error("no content in Employees ");
			throw new EmptyListException("Employees list are empty");
		}
		LOGGER.info("calling list of Employees");
		return Employees;
	}

	@Override
	public Employee updateEmployee(int employeeId, EmployeeDTO employeeDTO) {
		if(!(employeeRepository.existsById(employeeId))){
			throw new InvoiceNotFoundException();
		}
		Date updatedDate = new Date();
		Employee employee = employeeRepository.findById(employeeId).get();
		Role role = roleRepository.findById(employeeDTO.getRoleId());
		try {
			employee.setRole(role);
			employee.setCountry(employeeDTO.getCountry());
			employee.setSerialNumber(employeeDTO.getSerialNumber());
			employee.setFirstName(employeeDTO.getFirstName());
			employee.setLastName(employeeDTO.getLastName());
			employee.setEmail(employeeDTO.getEmail());
			employee.setPassword(employeeDTO.getPassword());
			employee.setMobileNumber(employeeDTO.getMobileNumber());
			return employeeRepository.save(employee);
		}catch(Exception exception){
			LOGGER.error("error while update invoice ");
			LOGGER.error("exception message " + exception.getMessage());
			LOGGER.error(exception.getStackTrace());
			return employeeRepository.findBySerialNumber(employee.getSerialNumber());
		}

	}

	@Override
	public Optional<Employee> getEmployee(int id) {
		if(employeeRepository.findById(id)==null){
			throw new EmployeeNotFoundException();
		}
		else {
			Optional<Employee> employee = employeeRepository.findById(id);
			return employee;
		}	}

}
