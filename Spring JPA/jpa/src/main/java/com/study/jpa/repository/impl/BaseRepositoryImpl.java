/*
package com.study.jpa.repository.impl;

import com.study.jpa.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

public class BaseRepositoryImpl<T, ID extends Serializable>
		extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {

		super(entityInformation, entityManager);

	}

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public T logicDelete(ID id) {
        return null;
    }
}
*/
