package com.study.jpa.controller;

import com.study.jpa.entity.CustomerOrder;
import com.study.jpa.result.Result;
import com.study.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 查询所有订单信息
	 *
	 * @return
	 */
	@GetMapping("main/all")
	public Result mainViews() {
		List<CustomerOrder> customerOrders = orderService.findAll();
		return Result.ok(customerOrders);
	}

	@GetMapping("find/one")
	public Result findOne() {
		return Result.ok(orderService.findOne(1));
	}

	/**
	 * 价格范围查询
	 *
	 * @param low
	 *            低价
	 * @param high
	 *            高价
	 */
	@GetMapping("price/between")
	public Result priceBetween(
			@RequestParam(value = "low", required = false) Integer low,
			@RequestParam(value = "high", required = false) Integer high) {

		return Result.ok(orderService.findByPriceBetween(low, high));

	}

	@GetMapping("price/specification")
	public Result priceSpec(
			@RequestParam(value = "customerId", required = false) Integer customerId,
			@RequestParam(value = "price", required = false) Integer price,
			@RequestParam(value = "date", required = false) Date date) {

		return Result
				.ok(orderService.findBySpecification(customerId, date, price));

	}

}
