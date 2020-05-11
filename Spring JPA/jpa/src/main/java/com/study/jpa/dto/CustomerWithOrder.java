package com.study.jpa.dto;

import com.study.jpa.entity.Customer;
import com.study.jpa.entity.CustomerOrder;

import java.util.List;

public class CustomerWithOrder {

    private Customer customer;

    private List<CustomerOrder> customerOrder;

    public CustomerWithOrder() {
    }

    public CustomerWithOrder(Customer customer, List<CustomerOrder> customerOrder) {
        this.customer = customer;
        this.customerOrder = customerOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CustomerOrder> getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(List<CustomerOrder> customerOrder) {
        this.customerOrder = customerOrder;
    }
}
