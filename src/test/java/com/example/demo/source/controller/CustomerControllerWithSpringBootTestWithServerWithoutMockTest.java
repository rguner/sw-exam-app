package com.example.demo.source.controller;

import com.example.demo.source.exception.ElementNotFoundException;
import com.example.demo.source.model.Customer;
import com.example.demo.source.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

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
public class CustomerControllerWithSpringBootTestWithServerWithoutMockTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static long customerIdInserted = 1;

    @Test
    public void save() {
        Customer customer = new Customer();
        customer.setName("Ramazan");
        customer.setAge(22);
        customer.setId(1L);

        ResponseEntity<Customer> customerResponse = restTemplate.postForEntity("/customer/",
                customer, Customer.class);

        assertTrue(customerResponse.getStatusCode().is2xxSuccessful());
        customerIdInserted = customerResponse.getBody().getId();
    }

    @Test
    public void getCustomerByIdWhenCustomerServiceReturnsCustomer() {

        ResponseEntity<Customer> customerResponse = restTemplate.getForEntity("/customer/" + customerIdInserted, Customer.class);

        assertTrue(customerResponse.getStatusCode().is2xxSuccessful());
        System.out.println(customerResponse.getBody());

    }


    @Test
    public void getAllCustomers() {


        ResponseEntity<List> customerResponse = restTemplate.getForEntity("/customer", List.class);

        assertTrue(customerResponse.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void deleteAll() {

        restTemplate.delete("/customer");
    }

}
