package com.careeroffice.dao;

import com.careeroffice.model.UserCompany;

import java.util.List;

public class UserCompanyDao implements CrudDao<UserCompany, String> {

    @Override
    public UserCompany findOne(String id) {
        return null;
    }

    @Override
    public List<UserCompany> findAll() {
        return null;
    }

    @Override
    public boolean save(UserCompany obj) {
        return false;
    }

    @Override
    public boolean update(UserCompany obj) {
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
