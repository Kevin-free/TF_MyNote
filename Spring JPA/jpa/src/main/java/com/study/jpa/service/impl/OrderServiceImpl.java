package com.study.jpa.service.impl;

import com.study.jpa.dao.OrderMapper;
import com.study.jpa.entity.CustomerOrder;
import com.study.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<CustomerOrder> findAll() {
		return orderMapper.findAll();
	}

	@Override
	public Optional<CustomerOrder> findOne(Integer id) {
		return orderMapper.findById(id);
	}

	@Override
	public void add(CustomerOrder customerOrder) {
		orderMapper.save(customerOrder);
	}

	@Override
	public void delete(CustomerOrder customerOrder) {
		orderMapper.delete(customerOrder);
	}

	@Override
	public void deleteById(Integer id) {
		orderMapper.deleteById(id);
	}

	@Override
	public List<CustomerOrder> findByPriceBetween(Integer low, Integer high) {
		return orderMapper.findByPriceBetween(low, high);
	}

	@Override
	public List<CustomerOrder> findBySpecification(Integer customerId,
			Date date, Integer price) {

		return orderMapper.findAll((root, query, cb) -> {

			// 创建一个条件的集合
			List<Predicate> predicates = new ArrayList<>();

			// 判断传过来的customerId是否为null,如果不为null就加到条件中
			if (Objects.nonNull(customerId)) {
				predicates.add(cb.equal(
						root.get("customerId").as(Integer.class), customerId));
			}

			if (Objects.nonNull(price)) {
				predicates.add(
						cb.equal(root.get("price").as(Integer.class), price));
			}

			if (Objects.nonNull(date)) {
				predicates.add(
						cb.equal(root.get("createTime").as(Date.class), date));
			}

			// 再创建一个长度为上面满足条件的个数的集合
			Predicate[] pre = new Predicate[predicates.size()];

			return query.where(predicates.toArray(pre)).getRestriction();

		});
	}
}
