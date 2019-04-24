package com.careeroffice.service;

import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.UserDao;
import com.careeroffice.model.User;

/**
 * Handles login validity and attempts.
 * Facade pattern
 */
public class LoginService implements IPersistenceService<User> {

    private UserDao userDao = (UserDao) DaoFactory.getDao( DaoEnum.UserDao );

    public LoginService() {}

    /**
     * Authenticates a user with the provided username.
     *
     * @param username The username of the user.
     * @return The user object or null.
     */
    public User auth(String username) {
        return userDao.findOne( username );
    }

}