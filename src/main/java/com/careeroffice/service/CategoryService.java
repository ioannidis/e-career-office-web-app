package com.careeroffice.service;

import com.careeroffice.dao.CategoryDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Category;

import java.util.List;

public class CategoryService implements IPersistenceService<Category> {

    private CategoryDao categoryDao = (CategoryDao) DaoFactory.getDao( DaoEnum.CategoryDao );

    public Category findOne(int id) {
        return categoryDao.findOne(id);
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
