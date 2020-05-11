package com.study.jpa.controller;

import com.study.jpa.dto.CustomerWithOrder;
import com.study.jpa.entity.Customer;
import com.study.jpa.repository.CustomerRepository;
import com.study.jpa.result.Result;
import com.study.jpa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * 查询带顾客信息的该顾客的订单信息
	 *
	 * @return
	 */
	@GetMapping("main/view")
	public Result mainView() {
		return Result.ok(customerService.mainViews());
	}

	/**
	 * 查询所有顾客
	 *
	 * @return
	 */
	@GetMapping("main/all")
	public Result mainAll() {
		return Result.ok(customerService.findAll());
	}

	@GetMapping("find/last/name")
	public Result<List<Customer>> findLastName(){
		return Result.ok(customerRepository.findByLastNameContaining("er"));
	}

}
