package com.example.demo.source.repository;


import com.example.demo.source.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
