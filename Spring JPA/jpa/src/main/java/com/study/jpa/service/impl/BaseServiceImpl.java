package com.study.jpa.service.impl;

import com.study.jpa.dao.BaseMapper;
import com.study.jpa.service.BaseService;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 张远志
 * @Date: 2020/4/24 14:22
 */

public class BaseServiceImpl<T, ID extends Serializable, R extends BaseMapper<T>>
		implements BaseService<T, ID> {

    protected R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;

        // 获取范型子类的类型
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        Class clazz = ((Class) params[0]);
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public Optional<T> findOne(ID id) {
        return Optional.empty();
    }

    @Override
    public void add(T t) {

    }

    @Override
    public void delete(T t) {

    }
}
