package com.careeroffice.service;

import com.careeroffice.dao.UserDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.User;
import com.careeroffice.service.factory.ServiceEnum;
import com.careeroffice.service.factory.ServiceFactory;

import java.util.List;

// Facade pattern
public class UserService implements IPersistenceService<User> {

    private UserDao userDao = (UserDao) DaoFactory.getDao(DaoEnum.UserDao);
    private RoleService roleService = (RoleService) ServiceFactory.getService(ServiceEnum.RoleService);
    private CompanyService companyService = (CompanyService) ServiceFactory.getService(ServiceEnum.CompanyService);
    private DepartmentService departmentService = (DepartmentService) ServiceFactory.getService(ServiceEnum.DepartmentService);

    public User findOne(String username) {
        User user = userDao.findOne(username);

        user.setRole(roleService.findOne(user.getRoleId()));
        user.setCompany(companyService.findOne(user.getCompanyId()));
        user.setDepartment(departmentService.findOne(user.getDepartmentId()));

        return user;
    }

    public List<User> findAll() {
        List<User> users = userDao.findAll();

        users.forEach(user -> {
            user.setRole(roleService.findOne(user.getRoleId()));
            user.setCompany(companyService.findOne(user.getCompanyId()));
            user.setDepartment(departmentService.findOne(user.getDepartmentId()));
        });

        return users;
    }

    public void save(String username, String password, String firstName, String lastName,
                     String phone, String email, String role) {
        userDao.save(new User(username, password, firstName, lastName, phone, email, role));
    }

    public int count() {
        return userDao.count();
    }
}