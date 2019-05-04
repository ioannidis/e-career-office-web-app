package com.careeroffice.service;

import com.careeroffice.dao.CompanyDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Company;

import java.util.List;

public class CompanyService implements IPersistenceService<Company> {

    private CompanyDao companyDao = (CompanyDao) DaoFactory.getDao(DaoEnum.CompanyDao);

    public Company findOne(String id) {
        return companyDao.findOne(id);
    }

    public List<Company> findAll() {
        return companyDao.findAll();
    }

    public boolean save(Company obj) {
        return companyDao.save(obj);
    }

    public boolean update(Company obj) {
        return companyDao.update(obj);
    }

    public boolean delete(String id) {
        return companyDao.delete(id);
    }

    public int count() {
        return companyDao.count();
    }
}
