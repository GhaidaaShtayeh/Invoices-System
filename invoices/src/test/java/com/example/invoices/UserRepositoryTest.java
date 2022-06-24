package com.example.invoices;

import com.example.invoices.model.Employee;
import com.example.invoices.model.Role;
import com.example.invoices.repository.IEmployeeRepository;
import com.example.invoices.utilite.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    IEmployeeRepository employeeRepository;

    @Test
    public void testCreatEmployee(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role role =  new Role(Type.SUPER_USER,"test");



        String rawPassword = "nam2020";


        String encodedPassword = passwordEncoder.encode(rawPassword);
        Employee newEmployee = new Employee(22222,"test10","test002",role,"nam@codejava.net","00501","Palestine",encodedPassword);
        Employee savedEmployee = employeeRepository.save(newEmployee);
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }
}
