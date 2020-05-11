package com.kevin.studyjpa.repository;

import com.kevin.studyjpa.entity.Person;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersonRepository extends Repository<Person, Long> {
    List<Person> findByName(String name);
}
