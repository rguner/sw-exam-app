package com.example.demo.source.controller;

import com.example.demo.source.Customer;
import com.example.demo.source.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/customers")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {

        try {
            return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }

}
