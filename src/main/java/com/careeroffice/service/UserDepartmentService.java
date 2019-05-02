package com.careeroffice.service;

import com.careeroffice.dao.DepartmentDao;
import com.careeroffice.dao.UserDepartmentDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.UserDepartment;

public class UserDepartmentService implements IPersistenceService<UserDepartment> {

    private UserDepartmentDao userDepartmentDao = (UserDepartmentDao) DaoFactory.getDao(DaoEnum.UserDepartmentDao);
    private DepartmentDao departmentDao = (DepartmentDao) DaoFactory.getDao(DaoEnum.DepartmentDao);

    private void setupRelationships(UserDepartment userDepartment) {
        userDepartment.setDepartment(departmentDao.findOne(userDepartment.getDepartmentId()));
    }

    public UserDepartment findOne(String id) {
        UserDepartment userDepartment = userDepartmentDao.findOne(id);

        setupRelationships(userDepartment);

        return userDepartment;
    }
}
