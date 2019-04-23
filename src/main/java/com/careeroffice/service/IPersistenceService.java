package com.careeroffice.service;

import java.util.List;

public interface IPersistenceService<T>{
    T findOne();

    List<T> findAll();

    boolean save();

    boolean update();

    boolean delete();

    int count();
}
