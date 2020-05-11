package com.study.jpa.repository;

import com.study.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerByExampleRepository extends JpaRepository<Customer, String> {
    @Query("select c from Customer c where c.firstName = ?1")
    Customer findByFirstName(String firstName);
}
