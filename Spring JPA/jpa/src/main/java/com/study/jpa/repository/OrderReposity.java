package com.study.jpa.repository;

import com.study.jpa.entity.CustomerOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderReposity extends CrudRepository<CustomerOrder, Integer> {
}
