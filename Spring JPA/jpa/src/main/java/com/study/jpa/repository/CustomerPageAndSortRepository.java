package com.study.jpa.repository;

import com.study.jpa.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerPageAndSortRepository extends PagingAndSortingRepository<Customer, Long> {
}
