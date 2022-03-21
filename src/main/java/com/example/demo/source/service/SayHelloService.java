package com.example.demo.source.service;

import org.springframework.stereotype.Service;

@Service
public class SayHelloService {

    private static final String HELLO = "Hello!";
    public String sayXHello(Integer helloCount) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < helloCount; i++) {
            stringBuilder.append(HELLO);
        }
        return stringBuilder.toString();
    }
}
