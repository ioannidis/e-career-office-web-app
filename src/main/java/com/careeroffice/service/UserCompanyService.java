package com.careeroffice.service;

import com.careeroffice.dao.CompanyDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.pivot.UserCompanyDao;
import com.careeroffice.model.UserCompany;

import java.util.List;

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

    public List<UserCompany> findAll() {
        List<UserCompany> userCompanies = userCompanyDao.findAll();

        userCompanies.forEach(this::setupRelationships);

        return userCompanies;
    }

    public UserCompany save(UserCompany obj) {
        return userCompanyDao.save(obj);
    }

    public UserCompany update(UserCompany obj) {
        return userCompanyDao.update(obj);
    }

    public boolean delete(String id) {
        return userCompanyDao.delete(id);
    }

    public int count() {
        return userCompanyDao.count();
    }
}
