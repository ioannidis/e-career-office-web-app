package com.careeroffice.service;

import com.careeroffice.dao.CompanyDao;
import com.careeroffice.dao.UserDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.pivot.UserCompanyDao;
import com.careeroffice.dao.pivot.UserDepartmentDao;
import com.careeroffice.model.User;
import com.careeroffice.model.UserCompany;
import com.careeroffice.model.UserDepartment;

// Facade pattern
public class RegistrationService implements IPersistenceService<User> {

    private UserDao userDao = (UserDao) DaoFactory.getDao(DaoEnum.UserDao);
    private UserCompanyDao userCompanyDao = (UserCompanyDao) DaoFactory.getDao( DaoEnum.UserCompanyDao );
    private UserDepartmentDao userDepartmentDao = (UserDepartmentDao) DaoFactory.getDao( DaoEnum.UserDepartmentDao );

    public RegistrationService() { }

    public void createUser(String username, String password, String firstName, String lastName,
                           String phone, String email, String role) {
        userDao.save(new User(username, password, firstName, lastName, phone, email, role));
    }

    public void createUserExternal(String username, String password, String firstName, String lastName,
                           String phone, String email, String role, String companyId) {
        userDao.save(new User(username, password, firstName, lastName, phone, email, role));
        userCompanyDao.save( new UserCompany( username, companyId ) );
    }

    public void createUserStudent(String username, String password, String firstName, String lastName,
                                   String phone, String email, String role, String departmentId) {
        userDao.save(new User(username, password, firstName, lastName, phone, email, role));
        userDepartmentDao.save( new UserDepartment( username, departmentId ) );
    }

    public boolean searchUserBy(String value, String field) {
        return userDao.searchUserBy(value, field);
    }

}
