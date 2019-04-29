package com.careeroffice.service;

import com.careeroffice.dao.RoleDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Role;

import java.util.List;

public class RoleService implements IPersistenceService<Role> {

    private RoleDao roleDao = (RoleDao) DaoFactory.getDao(DaoEnum.RoleDao);

    public Role findOne(String id) {
        return roleDao.findOne(id);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
