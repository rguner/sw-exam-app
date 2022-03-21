package com.example.demo.source.controller;

import com.example.demo.source.InttoWord;
import com.example.demo.source.Person;
import com.example.demo.source.Personrepository;
import com.example.demo.source.TranslateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    Personrepository personrepository;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPersons() {

        List<Person> persons = personrepository.findAll();
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

    @GetMapping("/dotranslate/{value}")
    public TranslateResult doTranslade(@PathVariable Integer value) {

        TranslateResult result = new TranslateResult();
        result.setId(value);
        result.setName(InttoWord.Translate(value));

        return result;
    }

}
