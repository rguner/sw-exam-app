package com.example.demo.source.service;

import com.example.demo.source.dto.response.SayHelloResponseDto;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class SayHelloService {

    private static final String HELLO = "Hello!";

    public SayHelloResponseDto sayXHello(Integer helloCount) {
        StringBuilder stringBuilder = new StringBuilder("");
        IntStream.range(0, helloCount).forEach(i -> stringBuilder.append(HELLO));

        SayHelloResponseDto result = new SayHelloResponseDto();
        result.setId(helloCount);
        result.setResult(stringBuilder.toString());
        return result;

    }
}
