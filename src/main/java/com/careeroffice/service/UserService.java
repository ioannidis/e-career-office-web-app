package com.careeroffice.service;

import com.careeroffice.dao.*;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.*;

import java.util.List;

// Facade pattern
public class UserService implements IPersistenceService<User> {

    private UserDao userDao = (UserDao) DaoFactory.getDao(DaoEnum.UserDao);
    private RoleDao roleDao = (RoleDao) DaoFactory.getDao(DaoEnum.RoleDao);
    private CompanyDao companyDao = (CompanyDao) DaoFactory.getDao(DaoEnum.CompanyDao);
    private UserCompanyDao userCompanyDao = (UserCompanyDao) DaoFactory.getDao(DaoEnum.UserCompanyDao);
    private DepartmentDao departmentDao = (DepartmentDao) DaoFactory.getDao(DaoEnum.DepartmentDao);
    private UserDepartmentDao userDepartmentDao = (UserDepartmentDao) DaoFactory.getDao(DaoEnum.UserDepartmentDao);

    private void setupRelationships(User user) {
        Role role = roleDao.findOne(user.getRoleId());
        user.setRole(role);

        UserCompany userCompany = userCompanyDao.findOne(user.getUsername());

        if (userCompany != null) {
            Company company = companyDao.findOne(userCompany.getCompanyId());
            userCompany.setCompany(company);
            user.setUserCompany(userCompany);
        }

        UserDepartment userDepartment = userDepartmentDao.findOne(user.getUsername());

        if (userDepartment != null) {
            Department department = departmentDao.findOne(userDepartment.getDepartmentId());
            userDepartment.setDepartment(department);
            user.setUserDepartment(userDepartment);
        }
    }

    private void setupSkills(User user,Skills skills) {
        user.setUserSkills(skills);
    }

    public User findOne(String username) {
        User user = userDao.findOne(username);

        setupRelationships(user);

        return user;
    }

    public List<User> findAll() {
        List<User> users = userDao.findAll();

        users.forEach(this::setupRelationships);

        return users;
    }

    public boolean save(User user) {
        return userDao.save(user);
    }

    public boolean update(User user) {
        return userDao.update(user);
    }

    public boolean delete(String id) {
        return userDao.delete(id);
    }

    public int count() {
        return userDao.count();
    }

    public List<User> findStudents() {
        List<User> users = userDao.findStudents();
        users.forEach(this::setupRelationships);
        for (User student:users
             ) {
            String skills = userDao.findStudentSkills(student.getUsername(),"skills");
            String slug = userDao.findStudentSkills(student.getUsername(),"slug");
            Skills skill = new Skills(student.getUsername(),skills,slug);
            setupSkills(student,skill);
        }
        return users;
    }
}