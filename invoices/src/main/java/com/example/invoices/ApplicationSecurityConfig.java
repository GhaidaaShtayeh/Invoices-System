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
                .antMatchers("/login").permitAll()
                .antMatchers("/invoice/viewList").permitAll()
                .antMatchers("/invoice/getInvoicesEmployee/{serialNumber}").permitAll()
                .antMatchers("/invoice/search/{serialNumber}").permitAll()
                .antMatchers("/invoiceItem/updateQuantity/{serialNumber}").permitAll()
                .antMatchers("/invoiceItem/viewList/{serialNumber}").permitAll()
                .antMatchers("/invoiceItem/save").permitAll()
                .antMatchers("/customer/viewList").hasRole("SUPER_USER")
                .antMatchers("/customer/deleteCustomer/{customerId}").hasRole("SUPER_USER")
                .antMatchers("/invoice/save").hasAnyRole("SUPER_USER" ,"USER")
                .antMatchers("/invoice/dashboard").hasRole("SUPER_USER")
                .antMatchers("/invoice/update/{id}").hasAnyRole("SUPER_USER","USER")
                .antMatchers("/customer/save").hasRole("SUPER_USER")
                .antMatchers("/getCustomer/{invoiceId}").hasRole("SUPER_USER")
                .antMatchers("/item/viewList").hasRole("SUPER_USER")
                .antMatchers("/item/save").hasRole("SUPER_USER")
                .antMatchers("/invoice/get-invoice/{serialNumber}").hasRole("SUPER_USER")
                .antMatchers("/invoice/deleteInvoice/{invoiceId}").hasRole("SUPER_USER")
                .antMatchers("/invoicehistory/get-invoice-history/{serialNumber}").hasAnyRole("SUPER_USER","AUDITOR")
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
