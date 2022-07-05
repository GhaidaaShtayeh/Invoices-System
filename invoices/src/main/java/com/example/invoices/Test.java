
package com.example.invoices;
import com.example.invoices.model.Customer;
import com.example.invoices.model.Invoice;
import com.example.invoices.repository.EmployeeRepository;
import com.example.invoices.repository.InvoiceRepository;
import com.example.invoices.utilite.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.invoices.model.Employee;
import com.example.invoices.model.Role;
@Component
public class Test{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public void testCreatEmployee(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role role =new Role(Type.AUDITOR,"test");
        String rawPassword = "0000";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Employee newEmployee = new Employee(2121,"test10","test002",role,"gha@gmail.com","123456","Palestine",encodedPassword);
        Employee savedEmployee = employeeRepository.save(newEmployee);

    }
    /*public void testCreatInvoice(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role role =new Role(Type.SUPER_USER,"test");
        Customer customer = new Customer(12377 , "gha", "tets", "123456", "123456789");
        //Employee employee = new Employee(1144,"ggg","111",role,"dd@ddd","4545","pales","0000");
        String rawPassword = "0000";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Employee newEmployee = new Employee(1144,"ggg","111",role,"dd@ddd","4545","pales",encodedPassword);
        Invoice invoice = new Invoice(11111,"complete","2020-05-07",)
        Employee savedEmployee = employeeRepository.save(newEmployee);

    }*/



}