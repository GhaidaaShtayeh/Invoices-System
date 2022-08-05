package com.example.invoices;

import com.example.invoices.jwt.JwtTokenFilter;
import com.example.invoices.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity(debug = true)
@CrossOrigin
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private EmployeeRepository userRepo;
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                username -> (UserDetails) userRepo.findByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User " + username + " not found.")));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
        .antMatchers("/employee/save").permitAll()
                .antMatchers("/employee/viewList").permitAll()
                .antMatchers("/employee/update/{id}").permitAll()
                .antMatchers("/employee/get-employee/{id}").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/invoice/viewList").permitAll()
                .antMatchers("/invoice/getInvoicesEmployee/{serialNumber}").permitAll()
                .antMatchers("/invoice/search/{serialNumber}").permitAll()
                .antMatchers("/invoiceItem/updateQuantity/{serialNumber}").permitAll()
                .antMatchers("/invoiceItem/viewList/{serialNumber}").permitAll()
                .antMatchers("/invoiceItem/save").permitAll()
                .antMatchers("/customer/viewList").permitAll()
                .antMatchers("/customer/deleteCustomer/{customerId}").permitAll()
                .antMatchers("/invoice/save").permitAll()
                .antMatchers("/invoice/dashboard").permitAll()
                .antMatchers("/invoice/update/{id}").permitAll()
                .antMatchers("/customer/save").permitAll()
                .antMatchers("/getCustomer/{invoiceId}").permitAll()
                .antMatchers("/item/viewList").permitAll()
                .antMatchers("/item/save").permitAll()
                .antMatchers("/invoice/get-invoice/{serialNumber}").permitAll()
                .antMatchers("/invoice/deleteInvoice/{invoiceId}").permitAll()
                .antMatchers("/invoicehistory/get-invoice-history/{serialNumber}").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
    }
}
