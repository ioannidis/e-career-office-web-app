package com.careeroffice.service;

import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.UserDao;
import com.careeroffice.model.User;

// Facade pattern
public class UserService implements IPersistenceService<User> {

    UserDao userDao = (UserDao) DaoFactory.getDao( DaoEnum.UserDao );

    public User findOne(String username) {
        return userDao.findOne( username );
    }

    public void save(String username, String password, String firstName, String lastName, String phone, String email, String role) {
        userDao.save( new User( username, password, firstName, lastName, phone, email, role ) );
    }

    public int count() {
        return userDao.count();
    }

}