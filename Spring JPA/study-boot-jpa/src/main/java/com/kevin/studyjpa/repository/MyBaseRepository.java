package com.kevin.studyjpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface MyBaseRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findByID(ID id);

    <S extends T> S save(S entity);
}
