package com.study.jpa.service;

import java.util.Optional;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: 张远志
 * @Date: 2020/4/24 14:17
 */
public interface BaseService<T, ID extends Serializable> {

	/**
	 * 查询所有
	 */
	List<T> findAll();

	/**
	 * 查询单个
	 *
	 * @param id
	 *            主键id
	 */
	Optional<T> findOne(ID id);

	/**
	 * 添加单个
	 *
	 * @param t
	 *            要添加的对象
	 */
	void add(T t);

	/**
	 * 删除单个
	 *
	 * @param t
	 *            要删除的对象
	 */
	void delete(T t);

}
