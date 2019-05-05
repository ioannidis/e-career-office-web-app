package com.careeroffice.service;

import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.CvDao;
import com.careeroffice.model.Cv;
import com.careeroffice.service.IPersistenceService;

public class CvService implements IPersistenceService<Cv> {

    private CvDao cvDao = (CvDao) DaoFactory.getDao(DaoEnum.CvDao);

    public Cv findOne(String username) {
        return cvDao.findOne(username);
    }

    public void save(String username, String file_url) {
        Cv newCv = new Cv(username, file_url);
        cvDao.save(newCv);
    }

    public void update(Cv object) {
        cvDao.update(object);
    }
}
