package com.example.demo.source.controller;

import com.example.demo.source.model.Customer;
import com.example.demo.source.repository.CustomerRepository;
import com.example.demo.source.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer) {
        return  customerService.save(customer);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

}
