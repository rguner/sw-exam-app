package com.example.demo.source.service;

import com.example.demo.source.dto.request.CustomerRequestDto;
import com.example.demo.source.exception.ElementNotFoundException;
import com.example.demo.source.model.Customer;
import com.example.demo.source.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        customer.setName(customerRequestDto.getName());
        customer.setAge(customerRequestDto.getAge());
        customer = null;
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new ElementNotFoundException("Customer not exist, id: " + id);
        }
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }


}
