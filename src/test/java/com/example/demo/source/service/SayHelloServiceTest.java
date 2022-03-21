package com.example.demo.source.service;

import com.example.demo.source.dto.request.CustomerRequestDto;
import com.example.demo.source.dto.response.SayHelloResponseDto;
import com.example.demo.source.exception.ElementNotFoundException;
import com.example.demo.source.model.Customer;
import com.example.demo.source.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SayHelloServiceTest {

    @InjectMocks
    private SayHelloService sayHelloService;

    @Mock
    private CustomerRepository customerRepository;

    private static final String HELLO = "Hello!";

    @Test
    public void sayXHelloWhenParameterIs1() {
        SayHelloResponseDto sayHelloResponseDto = sayHelloService.sayXHello(1);
        assertEquals(1, sayHelloResponseDto.getId());
        assertEquals(HELLO, sayHelloResponseDto.getResult());
    }

    @Test
    public void sayXHelloWhenParameterIs2() {
        SayHelloResponseDto sayHelloResponseDto = sayHelloService.sayXHello(2);
        assertEquals(2, sayHelloResponseDto.getId());
        assertEquals(HELLO+HELLO, sayHelloResponseDto.getResult());
    }

    @Test
    public void sayXHelloWhenParameterIs100() {
        SayHelloResponseDto sayHelloResponseDto = sayHelloService.sayXHello(100);
        assertEquals(100, sayHelloResponseDto.getId());
        assertEquals(HELLO.length()*100, sayHelloResponseDto.getResult().length());
    }

}
