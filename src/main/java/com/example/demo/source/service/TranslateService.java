package com.example.demo.source.service;

import com.example.demo.source.dto.response.TranslateResponseDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class TranslateService {

    private Map<Integer, String> translateMap;

    @PostConstruct
    private void init() {
        translateMap = new HashMap<>();
        translateMap.put(0, "Zero");
        translateMap.put(1, "One");
        translateMap.put(2, "Two");
        translateMap.put(3, "Three");
        translateMap.put(4, "Four");
        translateMap.put(5, "Five");
        translateMap.put(6, "Six");
        translateMap.put(7, "Seven");
        translateMap.put(8, "Eight");
        translateMap.put(9, "Nine");
        translateMap.put(10, "Ten");
    }

    public TranslateResponseDto translate(Integer value) {
        TranslateResponseDto result = new TranslateResponseDto();
        result.setId(value);
        result.setName(translateInner(value));
        return result;
    }

    private String translateInner(Integer caseIndex) {

        String response = translateMap.get(caseIndex);
        return response != null ? response : "Not supported";
    }
}
