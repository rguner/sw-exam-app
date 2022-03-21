package com.example.demo.source.service;

import com.example.demo.source.dto.request.CustomerRequestDto;
import com.example.demo.source.exception.ElementNotFoundException;
import com.example.demo.source.model.Customer;
import com.example.demo.source.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void getCustomerByIdWhenCustomerRepositoryReturnsCustomer() {
        Customer customer = new Customer();
        customer.setId(1000L);
        Optional<Customer> optionalCustomer = Optional.of(customer);
        when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);

        assertEquals(1000L, customerService.getCustomerById(anyLong()).getId().longValue());
    }

    @Test
    public void getCustomerByIdWhenCustomerRepositoryReturnsEmpty() {
        Optional<Customer> optionalCustomer = Optional.ofNullable(null);
        when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);

        ElementNotFoundException thrown = assertThrows(ElementNotFoundException.class, () -> {
            customerService.getCustomerById(anyLong());
        });
        assertTrue(thrown.getMessage().contains("Customer not exist, id"));
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setId(1000L);
        when(customerRepository.save(any())).thenReturn(customer);

        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        assertEquals(1000L, customerService.save(customerRequestDto).getId());
    }



}
