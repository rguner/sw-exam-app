package com.example.demo.source.configuration;

import com.example.demo.source.model.Person;
import com.example.demo.source.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@Configuration
public class InitConfiguration {

    @Value(value = "${system.environment:PROD}")
    private String systemEnvironment;

    @Autowired
    private PersonService personService;

    @PostConstruct
    private void init() {
        if ("TEST".equalsIgnoreCase(systemEnvironment)) {
            personService.save(new Person("John", "Doe"));
        }
    }
}
