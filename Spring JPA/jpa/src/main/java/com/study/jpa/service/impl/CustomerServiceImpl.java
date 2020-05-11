package com.study.jpa.service.impl;

import com.study.jpa.dao.CustomerMapper;
import com.study.jpa.dao.OrderMapper;
import com.study.jpa.dto.CustomerWithOrder;
import com.study.jpa.entity.Customer;
import com.study.jpa.entity.CustomerOrder;
import com.study.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<Customer> findAll() {
		return customerMapper.findAll();
	}

	@Override
	public Optional<Customer> findOne(Integer id) {
		return customerMapper.findById(id);
	}

	@Override
	public void add(Customer customer) {
		customerMapper.save(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerMapper.delete(customer);
	}

	@Override
	public void deleteById(Integer id) {
		customerMapper.deleteById(id);
	}

	@Override
	public List<CustomerWithOrder> mainViews() {
		// 查询所有customer
		List<Customer> customerList = customerMapper.findAll();
		return customerList.stream().map(customer -> {

			// 创建CustomerWithOrder对象
			CustomerWithOrder customerWithOrder = new CustomerWithOrder();
			customerWithOrder.setCustomer(customer);

			// 查找与customer对应的order
			Example<CustomerOrder> example = Example
					.of(new CustomerOrder(customer.getId()));
			List<CustomerOrder> customerOrders = orderMapper.findAll(example);

			// 将对应order放入CustomerWithOrder对象
			customerWithOrder.setCustomerOrder(customerOrders);

			return customerWithOrder;

		}).collect(Collectors.toList());
	}
}
