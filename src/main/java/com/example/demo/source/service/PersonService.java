package com.example.demo.source.service;

import com.example.demo.source.model.Person;
import com.example.demo.source.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }


}