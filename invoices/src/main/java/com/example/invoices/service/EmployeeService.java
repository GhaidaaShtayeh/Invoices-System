package com.example.invoices.service;

import com.example.invoices.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	public Employee getEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public Employee deleteEmployee(int empId);
}
