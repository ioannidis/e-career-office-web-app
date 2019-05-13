package com.careeroffice.service;

import com.careeroffice.dao.CategoryDao;
import com.careeroffice.dao.ClassifiedDao;
import com.careeroffice.dao.CompanyDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Category;
import com.careeroffice.model.Classified;
import com.careeroffice.model.Company;
import com.careeroffice.service.pivot.KeywordClassifiedPivotService;

import java.util.List;

public class ClassifiedService implements IPersistenceService<Classified> {

    private ClassifiedDao classifiedDao = (ClassifiedDao) DaoFactory.getDao(DaoEnum.ClassifiedDao);
    private CompanyDao companyDao = (CompanyDao) DaoFactory.getDao(DaoEnum.CompanyDao);
    private CategoryDao categoryDao = (CategoryDao) DaoFactory.getDao(DaoEnum.CategoryDao);
    private KeywordClassifiedPivotService keywordClassifiedPivotService = (KeywordClassifiedPivotService) DaoFactory.getDao(DaoEnum.KeywordClassifiedPivotDao);

    public ClassifiedService() {
    }

    private void setupRelationships(Classified classified) {

        Company company = companyDao.findOne(classified.getCompanyId());
        Category category = categoryDao.findOne(classified.getCategoryId());

        classified.setCompany(company);
        classified.setCategory(category);
    }

    public List<Classified> findAllByCompany(String companyId) {
        return classifiedDao.findAllByCompany(companyId);
    }

    public List<Classified> findAll() {
        return classifiedDao.findAll();
    }

    public String findClassifiedSkills(Integer id, String type) {
        return classifiedDao.findClassifiedSkills(id, type);
    }

    public Classified findOne(Integer id) {
        return classifiedDao.findOne(id);
    }

    public Classified save(Classified obj) {
        return classifiedDao.save(obj);
    }

    public Classified update(Classified obj) {
        return classifiedDao.update(obj);
    }

    public boolean delete(Integer id) {
        return classifiedDao.delete(id);
    }

}
