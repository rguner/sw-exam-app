package com.example.demo.source.service;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class SayHelloService {

    private static final String HELLO = "Hello!";
    public String sayXHello(Integer helloCount) {
        StringBuilder stringBuilder = new StringBuilder("");
        IntStream.range(0, helloCount).forEach(i->stringBuilder.append(HELLO));
        return stringBuilder.toString();
    }
}
