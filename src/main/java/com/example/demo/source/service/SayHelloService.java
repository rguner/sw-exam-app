package com.example.demo.source.service;

import org.springframework.stereotype.Service;

@Service
public class SayHelloService {

    public String sayXHello(Integer x) {
        String xHello = "";
        for (int i = 0; i < x; i++) {
            xHello = xHello + "Hello!";
        }
        return xHello;
    }

    public String sayOnceHello() {
        return "Hello!";
    }

    public String sayTwiceHello() {
        return "Hello! Hello!";
    }
}
