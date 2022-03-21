package com.example.demo.source.service;

import com.example.demo.source.model.Customer;
import com.example.demo.source.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(1000L);
        Optional<Customer> optionalCustomer = Optional.of(customer);
        when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);

        assertEquals(1000L, customerService.getCustomerById(anyLong()).getId().longValue());
    }
}