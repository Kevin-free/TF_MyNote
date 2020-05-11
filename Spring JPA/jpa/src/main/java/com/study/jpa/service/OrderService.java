package com.study.jpa.service;

import com.study.jpa.entity.CustomerOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {

	/**
	 * 查询所有
	 */
	List<CustomerOrder> findAll();

	/**
	 * 查询单个
	 *
	 * @param id
	 */
	Optional<CustomerOrder> findOne(Integer id);

	/**
	 * 添加单个
	 */
	void add(CustomerOrder customerOrder);

	/**
	 * 删除单个
	 */
	void delete(CustomerOrder customerOrder);

	/**
	 * 根据id删除
	 */
	void deleteById(Integer id);

	/**
	 * 价格范围查询
	 *
	 * @param low
	 *            低价
	 * @param high
	 *            高价
	 */
	List<CustomerOrder> findByPriceBetween(Integer low, Integer high);

	List<CustomerOrder> findBySpecification(Integer customerId, Date date, Integer price);
}
