package com.example.demo.source.controller;

import com.example.demo.source.model.Person;
import com.example.demo.source.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @Test
    public void getAllPeople() throws Exception {
        List<Person> personList = new ArrayList<>();
        Person person = new Person();
        person.setId(1000L);
        personList.add(person);
        when(personService.getAllPeople()).thenReturn(personList);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person");
        MockMvcBuilders.standaloneSetup(personController).build().perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteAll() throws Exception {
        doAnswer(i -> {
            return null;
        }).when(personService).deleteAll();

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/person");
        MockMvcBuilders.standaloneSetup(personController).build().perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
