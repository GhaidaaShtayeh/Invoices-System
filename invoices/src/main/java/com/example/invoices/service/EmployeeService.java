package com.example.invoices.service;

import com.example.invoices.dto.EmployeeDTO;
import com.example.invoices.model.Employee;

public interface EmployeeService {

	public Employee saveEmployee(EmployeeDTO employee);
	public Employee getEmployee(Employee employee);

    Employee getEmployeeBySerialNumber(long serialNumber);
}
