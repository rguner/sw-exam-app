package com.example.demo.source.controller;

import com.example.demo.source.service.SayHelloService;
import com.example.demo.source.dto.response.SayHelloResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sayhello")
public class SayHelloController {

    @Autowired
    private SayHelloService sayHelloService;

    @GetMapping("/{helloCount}")
    public SayHelloResponseDto sayhello(@PathVariable Integer helloCount) {
        return sayHelloService.sayXHello(helloCount);
    }
}
