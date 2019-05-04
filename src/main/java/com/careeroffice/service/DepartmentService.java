package com.careeroffice.service;

import com.careeroffice.dao.DepartmentDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Department;

import java.util.List;

public class DepartmentService implements IPersistenceService<Department> {

    private DepartmentDao departmentDao = (DepartmentDao) DaoFactory.getDao(DaoEnum.DepartmentDao);

    public Department findOne(String id) {
        return departmentDao.findOne(id);
    }

    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    public Department save(Department obj) {
        return departmentDao.save(obj);
    }

    public Department update(Department obj) {
        return departmentDao.update(obj);
    }

    public boolean delete(String id) {
        return departmentDao.delete(id);
    }

    public int count() {
        return departmentDao.count();
    }
}
