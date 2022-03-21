package com.example.demo.source.controller;

import com.example.demo.source.model.Person;
import com.example.demo.source.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personrepository;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPersons() {

        List<Person> persons = personrepository.findAll();
        return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
    }

}
