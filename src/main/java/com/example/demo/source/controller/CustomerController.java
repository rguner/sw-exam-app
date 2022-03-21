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
    /*
        try {
            return new ResponseEntity<>(customerRepository.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return null;
        }

     */
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }

}
