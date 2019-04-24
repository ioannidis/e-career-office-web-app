package com.careeroffice.dao;

import java.util.List;

public interface CrudDao<T, K> {
    T findOne(K id);

    List<T> findAll();

    boolean save(T obj);

    boolean update(T obj);

    boolean delete(K id);

    int count();
}
