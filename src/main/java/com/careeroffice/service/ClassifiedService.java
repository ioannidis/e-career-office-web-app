package com.careeroffice.service;

import com.careeroffice.dao.ClassifiedDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Classified;

import java.util.List;

public class ClassifiedService  implements IPersistenceService<Classified> {

    ClassifiedDao classifiedDao = (ClassifiedDao) DaoFactory.getDao(DaoEnum.ClassifiedDao);

    public ClassifiedService() {
    }

    public List<Classified> findAllByCompany(String companyId) {
        return classifiedDao.findAllByCompany(companyId);
    }

    public List<Classified> findAll() {
        return classifiedDao.findAll();
    }

    public String findClassifiedSkills(Integer id, String type){return classifiedDao.findClassifiedSkills(id,type);}

    public Classified findOne(Integer id) {
        return classifiedDao.findOne(id);
    }

    public boolean save(Classified obj) {
        return classifiedDao.save(obj);
    }

    public boolean update(Classified obj) {
        return classifiedDao.update(obj);
    }

    public boolean delete(Integer id) {
        return classifiedDao.delete(id);
    }

}
