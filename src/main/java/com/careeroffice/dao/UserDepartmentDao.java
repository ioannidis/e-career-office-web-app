package com.careeroffice.dao;

import com.careeroffice.model.UserDepartment;

import java.util.List;

public class UserDepartmentDao implements CrudDao<UserDepartment, String> {

    @Override
    public UserDepartment findOne(String id) {
        return null;
    }

    @Override
    public List<UserDepartment> findAll() {
        return null;
    }

    @Override
    public boolean save(UserDepartment obj) {
        return false;
    }

    @Override
    public boolean update(UserDepartment obj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }
}
