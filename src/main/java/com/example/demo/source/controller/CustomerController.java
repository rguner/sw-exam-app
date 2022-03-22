package com.example.demo.source.controller;

import com.example.demo.source.dto.request.CustomerRequestDto;
import com.example.demo.source.model.Customer;
import com.example.demo.source.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        return customerService.save(customerRequestDto);
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

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll() {
        customerService.deleteAll();
    }
}
