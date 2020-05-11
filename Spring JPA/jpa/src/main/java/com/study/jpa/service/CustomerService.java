package com.study.jpa.service;

import com.study.jpa.dto.CustomerWithOrder;
import com.study.jpa.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    /**
     * 查询所有
     */
    List<Customer> findAll();

    /**
     * 查询单个
     * @param id
     */
    Optional<Customer> findOne(Integer id);

    /**
     * 添加单个
     */
    void add(Customer customer);

    /**
     * 删除单个
     */
    void delete(Customer customer);

    /**
     * 根据id删除
     */
    void deleteById(Integer id);

    /**
     * 查询带顾客信息的该顾客的订单信息
     */
    List<CustomerWithOrder> mainViews();
}
