package com.careeroffice.service;

import com.careeroffice.dao.CategoryDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Category;

import java.util.List;

public class CategoryService implements IPersistenceService<Category> {

    private CategoryDao categoryDao = (CategoryDao) DaoFactory.getDao(DaoEnum.CategoryDao);

    public Category findOne(int id) {
        return categoryDao.findOne(id);
    }

    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category save(Category obj) {
        return categoryDao.save(obj);
    }

    public Category update(Category obj) {
        return categoryDao.update(obj);
    }

    public boolean delete(Integer id) {
        return categoryDao.delete(id);
    }

    public int count() {
        return categoryDao.count();
    }
}
