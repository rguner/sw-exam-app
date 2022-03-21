package com.example.demo.source.controller;

import com.example.demo.source.dto.response.TranslateResponseDto;
import com.example.demo.source.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dotranslate")
public class TranslateController {

    @Autowired
    private TranslateService translateService;

    @GetMapping("/{value}")
    public TranslateResponseDto doTranslate(@PathVariable Integer value) {

        TranslateResponseDto result = new TranslateResponseDto();
        result.setId(value);
        result.setName(translateService.translate(value));

        return result;
    }
}
