package com.example.demo.source.service;

import com.example.demo.source.dto.request.CustomerRequestDto;
import com.example.demo.source.exception.ElementNotFoundException;
import com.example.demo.source.model.Customer;
import com.example.demo.source.model.Person;
import com.example.demo.source.repository.CustomerRepository;
import com.example.demo.source.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void save() {
        Person person = new Person();
        person.setId(1000L);
        when(personRepository.save(any())).thenReturn(person);

        assertEquals(1000L, personService.save(person).getId());
    }

    @Test
    public void getAllPeople() {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(1000L);
        personList.add(person);
        when(personRepository.findAll()).thenReturn(personList);

        assertEquals(1, personService.getAllPeople().size());
    }

    @Test
    public void deleteAll() {
        doAnswer(i -> {
            return null;
        }).when(personRepository).deleteAll();

        personService.deleteAll();
        verify(personRepository, atMost(1)).deleteAll();
    }



}
