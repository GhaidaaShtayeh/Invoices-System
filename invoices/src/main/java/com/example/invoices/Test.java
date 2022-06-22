
package com.example.invoices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.invoices.model.Employee;
import com.example.invoices.model.Role;
import com.example.invoices.repository.IEmployeeRepository;
@Component
public class Test{
	 @Autowired
	    IEmployeeRepository employeeRepository;
	
	public void testCreatEmployee(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role role =new Role(Role.Type.ROLE_USER,"test");
        String rawPassword = "0000";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Employee newEmployee = new Employee(1111,"test10","test002",role,"gh@gmail.com","00501","Palestine",encodedPassword);
        Employee savedEmployee = employeeRepository.save(newEmployee);

    }
	
	
}