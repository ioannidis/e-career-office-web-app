package com.careeroffice.service;

import com.careeroffice.dao.CompanyDao;
import com.careeroffice.dao.UserCompanyDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.UserCompany;

public class UserCompanyService implements IPersistenceService<UserCompany> {

    private UserCompanyDao userCompanyDao = (UserCompanyDao) DaoFactory.getDao(DaoEnum.UserCompanyDao);
    private CompanyDao companyDao = (CompanyDao) DaoFactory.getDao(DaoEnum.CompanyDao);

    private void setupRelationships(UserCompany userCompany) {
        userCompany.setCompany(companyDao.findOne(userCompany.getCompanyId()));
    }

    public UserCompany findOne(String id) {
        UserCompany userCompany = userCompanyDao.findOne(id);

        setupRelationships(userCompany);

        return userCompany;
    }
}
