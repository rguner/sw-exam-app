package com.example.demo.source.controller;

import com.example.demo.source.dto.response.TranslateResponseDto;
import com.example.demo.source.service.TranslateService;
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
public class TranslateControllerTest {

    @InjectMocks
    private TranslateController translateController;

    @Mock
    private TranslateService translateService;

    @Test
    public void sayHello() throws Exception {
        TranslateResponseDto translateResponseDto = new TranslateResponseDto();
        translateResponseDto.setId(5);
        translateResponseDto.setName("Five");
        when(translateService.translate(anyInt())).thenReturn(translateResponseDto);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dotranslate/{value}", 5);
        MockMvcBuilders.standaloneSetup(translateController).build().perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
