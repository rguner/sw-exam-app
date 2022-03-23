package com.example.demo.source.controller;

import com.example.demo.source.controller.advice.ControllerExceptionAdvice;
import com.example.demo.source.exception.ElementNotFoundException;
import com.example.demo.source.model.Customer;
import com.example.demo.source.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @InjectMocks
    private ControllerExceptionAdvice controllerExceptionAdvice;

    @Mock
    private CustomerService customerService;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(controllerExceptionAdvice)
                .build();
    }

    @Test
    public void getCustomerByIdWhenCustomerServiceReturnsCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1000L);
        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/{id}", 1);
        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("{\"id\":1000,\"name\":null,\"age\":null,\"createdAt\":null,\"updatedAt\":null}"));
    }

    @Test
    public void getCustomerByIdWhenCustomerServiceThrowsException() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(new ElementNotFoundException("Customer not exist, id: 1"));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/{id}", 1L);
        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    public void save() throws Exception {
        Customer customer = new Customer();
        customer.setName("Ramazan");
        customer.setAge(22);
        customer.setId(1000L);
        when(customerService.save(any())).thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer")
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1000L);
        customerList.add(customer);
        when(customerService.getAllCustomers()).thenReturn(customerList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer");
        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteAll() throws Exception {
        doAnswer(i -> {
            return null;
        }).when(customerService).deleteAll();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/customer");
        mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
