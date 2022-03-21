package com.example.demo.source.controller;

import com.example.demo.source.model.Person;
import com.example.demo.source.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }
}
