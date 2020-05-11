package com.study.jpa.dao;

import java.util.List;

import com.study.jpa.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderMapper extends JpaRepository<CustomerOrder, Integer>,
		JpaSpecificationExecutor<CustomerOrder> {

	/**
	 * 价格范围查询
	 *
	 * @param low
	 *            低价
	 * @param high
	 *            高价
	 */
	List<CustomerOrder> findByPriceBetween(Integer low, Integer high);

}
