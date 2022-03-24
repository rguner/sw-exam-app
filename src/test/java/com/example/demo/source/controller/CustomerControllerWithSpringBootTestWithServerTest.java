package com.example.demo.source.controller;

import com.example.demo.source.exception.ElementNotFoundException;
import com.example.demo.source.model.Customer;
import com.example.demo.source.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

// https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerWithSpringBootTestWithServerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCustomerByIdWhenCustomerServiceReturnsCustomer() {
        Customer customer = new Customer();
        customer.setId(1000L);
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        ResponseEntity<Customer> customerResponse = restTemplate.getForEntity("/customer/1", Customer.class);

        assertTrue(customerResponse.getStatusCode().is2xxSuccessful());
        assertTrue(customerResponse.getBody().equals(customer));
    }

    @Test
    public void getCustomerByIdWhenCustomerServiceThrowsException() {
        when(customerService.getCustomerById(anyLong())).thenThrow(new ElementNotFoundException("Customer not exist, id: 1"));

        ResponseEntity<Customer> customerResponse = restTemplate.getForEntity("/customer/1", Customer.class);
        assertTrue(customerResponse.getStatusCode().is4xxClientError());
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setName("Ramazan");
        customer.setAge(22);
        customer.setId(1000L);
        when(customerService.save(any())).thenReturn(customer);

        ResponseEntity<Customer> customerResponse = restTemplate.postForEntity("/customer/",
                customer, Customer.class);

        assertTrue(customerResponse.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1000L);
        customerList.add(customer);
        when(customerService.getAllCustomers()).thenReturn(customerList);

        ResponseEntity<List> customerResponse = restTemplate.getForEntity("/customer", List.class);

        assertTrue(customerResponse.getStatusCode().is2xxSuccessful());
        assertEquals(customerResponse.getBody().size(), customerList.size());
    }

    @Test
    public void deleteAll() {
        doAnswer(i -> {
            return null;
        }).when(customerService).deleteAll();

        restTemplate.delete("/customer");
    }

}
