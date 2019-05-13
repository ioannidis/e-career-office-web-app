package com.careeroffice.service;

import com.careeroffice.dao.CategoryDao;
import com.careeroffice.dao.ClassifiedDao;
import com.careeroffice.dao.CompanyDao;
import com.careeroffice.dao.KeywordDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.pivot.KeywordClassifiedPivotDao;
import com.careeroffice.model.*;

import java.util.List;

public class ClassifiedService implements IPersistenceService<Classified> {

    private ClassifiedDao classifiedDao = (ClassifiedDao) DaoFactory.getDao(DaoEnum.ClassifiedDao);
    private CompanyDao companyDao = (CompanyDao) DaoFactory.getDao(DaoEnum.CompanyDao);
    private CategoryDao categoryDao = (CategoryDao) DaoFactory.getDao(DaoEnum.CategoryDao);
    private KeywordDao keywordDao = (KeywordDao) DaoFactory.getDao(DaoEnum.KeywordDao);
    private KeywordClassifiedPivotDao keywordClassifiedPivotDao = (KeywordClassifiedPivotDao) DaoFactory.getDao(DaoEnum.KeywordClassifiedPivotDao);

    public ClassifiedService() {

    }

    private void setupRelationships(Classified classified) {
        Company company = companyDao.findOne(classified.getCompanyId());
        Category category = categoryDao.findOne(classified.getCategoryId());

        classified.setCompany(company);
        classified.setCategory(category);

        List<KeywordClassifiedPivot> keywordClassifiedPivotList = keywordClassifiedPivotDao.findByClassified(classified.getId());

        keywordClassifiedPivotList.forEach(keywordClassifiedPivot -> {
            keywordClassifiedPivot.setClassified(classified);

            Keyword keyword = keywordDao.findOne(keywordClassifiedPivot.getKeywordId());
            keywordClassifiedPivot.setKeyword(keyword);
        });

        classified.setKeywordClassifiedPivotList(keywordClassifiedPivotList);
    }

    public List<Classified> findAllByCompany(String companyId) {
        List<Classified> classifieds = classifiedDao.findAllByCompany(companyId);

        classifieds.forEach(this::setupRelationships);

        return classifieds;
    }

    public List<Classified> findAll() {
        List<Classified> classifieds = classifiedDao.findAll();

        classifieds.forEach(this::setupRelationships);

        return classifieds;
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
