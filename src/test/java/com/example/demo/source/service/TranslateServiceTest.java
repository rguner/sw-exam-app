package com.example.demo.source.service;

import com.example.demo.source.dto.response.SayHelloResponseDto;
import com.example.demo.source.dto.response.TranslateResponseDto;
import com.example.demo.source.repository.CustomerRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TranslateServiceTest {

    @InjectMocks
    private TranslateService translateService;

    @BeforeEach
    public void beforeClassFunction(){
        ReflectionTestUtils.invokeMethod(translateService, "init");
    }

    @Test
    public void translateWhenParameterIs1() {

        TranslateResponseDto translateResponseDto = translateService.translate(1);
        assertEquals(1, translateResponseDto.getId());
        assertEquals("One", translateResponseDto.getName());
    }

    @Test
    public void translateWhenParameterIs2() {
        TranslateResponseDto translateResponseDto = translateService.translate(2);
        assertEquals(2, translateResponseDto.getId());
        assertEquals("Two", translateResponseDto.getName());
    }

    @Test
    public void translateWhenParameterIs100() {
        TranslateResponseDto translateResponseDto = translateService.translate(100);
        assertEquals(100, translateResponseDto.getId());
        assertEquals("Not supported", translateResponseDto.getName());
    }

}
