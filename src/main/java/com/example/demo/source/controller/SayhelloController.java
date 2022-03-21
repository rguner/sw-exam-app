package com.example.demo.source.controller;

import com.example.demo.source.service.Sayhello;
import com.example.demo.source.dto.response.SayhelloResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayhelloController {

    @GetMapping("/sayhello/{value}")
    public SayhelloResult sayhello(@PathVariable Integer value) {

        SayhelloResult result = new SayhelloResult();
        result.setId(value);
        result.setResult(Sayhello.SayXHello(value));

        return result;
    }
}
