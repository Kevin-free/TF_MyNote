package com.study.jpa.dao;

import com.study.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerMapper extends JpaRepository<Customer, Integer> {
}
