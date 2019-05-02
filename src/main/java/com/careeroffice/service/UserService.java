package com.careeroffice.service;

import com.careeroffice.dao.CompanyDao;
import com.careeroffice.dao.DepartmentDao;
import com.careeroffice.dao.RoleDao;
import com.careeroffice.dao.UserDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.User;

import java.util.List;

// Facade pattern
public class UserService implements IPersistenceService<User> {

    private UserDao userDao = (UserDao) DaoFactory.getDao(DaoEnum.UserDao);
    private RoleDao roleDao = (RoleDao) DaoFactory.getDao(DaoEnum.RoleDao);
    private CompanyDao companyDao = (CompanyDao) DaoFactory.getDao(DaoEnum.CompanyDao);
    private DepartmentDao departmentDao = (DepartmentDao) DaoFactory.getDao(DaoEnum.DepartmentDao);

    public User findOne(String username) {
        User user = userDao.findOne(username);

        user.setRole(roleDao.findOne(user.getRoleId()));
        user.setCompany(companyDao.findOne(user.getCompanyId()));
        user.setDepartment(departmentDao.findOne(user.getDepartmentId()));

        return user;
    }

    public List<User> findAll() {
        List<User> users = userDao.findAll();

        users.forEach(user -> {
            user.setRole(roleDao.findOne(user.getRoleId()));
            user.setCompany(companyDao.findOne(user.getCompanyId()));
            user.setDepartment(departmentDao.findOne(user.getDepartmentId()));
        });

        return users;
    }

    public List<User> findStudents() {
        List<User> users = userDao.findStudents();

        users.forEach(user -> {
            user.setRole(roleDao.findOne(user.getRoleId()));
            user.setCompany(companyDao.findOne(user.getCompanyId()));
            user.setDepartment(departmentDao.findOne(user.getDepartmentId()));
        });

        return users;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public int count() {
        return userDao.count();
    }
}