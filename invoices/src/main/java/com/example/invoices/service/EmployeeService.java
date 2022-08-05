package com.example.invoices.service;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

	public Employee saveEmployee(EmployeeDTO employee);
	public Employee getEmployee(Employee employee);
    Employee getEmployeeBySerialNumber(long serialNumber);
	public List<Employee> getAllEmployee();
	public Employee updateEmployee(int employeeId, EmployeeDTO employeeDTO);
	public Optional<Employee> getEmployee(int id);
}
