package com.careeroffice.service;

import com.careeroffice.dao.UserDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.User;

// Facade pattern
public class RegistrationService implements IPersistenceService<User> {

    private UserDao userDao = (UserDao) DaoFactory.getDao(DaoEnum.UserDao);

    public RegistrationService() { }

    public void createUser(String username, String password, String firstName, String lastName,
                           String phone, String email, String role) {
        userDao.save(new User(username, password, firstName, lastName, phone, email, role));
    }

    public boolean searchUserBy(String value, String field) {
        return userDao.searchUserBy(value, field);
    }

}
