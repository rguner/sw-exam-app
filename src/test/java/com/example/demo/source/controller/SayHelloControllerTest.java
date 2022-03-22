package com.example.demo.source.controller;

import com.example.demo.source.dto.response.SayHelloResponseDto;
import com.example.demo.source.service.SayHelloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SayHelloControllerTest {

    @InjectMocks
    private SayHelloController sayHelloController;

    @Mock
    private SayHelloService sayHelloService;

    private static final String HELLO = "Hello!";

    @Test
    public void sayHello() throws Exception {
        SayHelloResponseDto sayHelloResponseDto = new SayHelloResponseDto();
        sayHelloResponseDto.setId(5);
        sayHelloResponseDto.setResult("");
        when(sayHelloService.sayXHello(anyInt())).thenReturn(sayHelloResponseDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sayhello/{helloCount}", 5);
        MockMvcBuilders.standaloneSetup(sayHelloController).build().perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
