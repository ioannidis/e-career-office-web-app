package com.careeroffice.service;

import com.careeroffice.dao.ClassifiedDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Classified;

import java.util.List;

public class ClassifiedService  implements IPersistenceService<Classified> {

    ClassifiedDao classifiedDao = (ClassifiedDao) DaoFactory.getDao(DaoEnum.ClassifieldDao);

    public ClassifiedService() {
    }

    public List<Classified> findAllByCompany(String companyId) {
        return classifiedDao.findAllByCompany(companyId);
    }

}
