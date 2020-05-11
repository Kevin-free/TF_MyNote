package com.kevin.studyjpa.controller;

import com.kevin.studyjpa.entity.Person;
import com.kevin.studyjpa.repository.PersonRepository;

import java.util.List;

public class OrderClient {
    private final PersonRepository repository;

    OrderClient(PersonRepository repository) {
        this.repository = repository;
    }

    void doOrder() {
        List<Person> persons = repository.findByName("tao");
    }
}
