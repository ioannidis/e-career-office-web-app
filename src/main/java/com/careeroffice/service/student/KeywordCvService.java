package com.careeroffice.service.student;

import com.careeroffice.dao.student.KeywordCvDao;
import com.careeroffice.model.Keyword;
import com.careeroffice.model.KeywordCvPivot;
import com.careeroffice.service.IPersistenceService;
import com.careeroffice.dao.KeywordDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import java.util.List;
import java.util.stream.Collectors;

public class KeywordCvService implements IPersistenceService<KeywordCvPivot> {

        KeywordDao keywordDao = (KeywordDao) DaoFactory.getDao( DaoEnum.KeywordDao );
        KeywordCvDao keywordCvDao = (KeywordCvDao) DaoFactory.getDao( DaoEnum.KeywordCvDao );

public List<Keyword> findByCv (int id) {
        return keywordCvDao.findByCv(id).stream()
        .map( x -> keywordDao.findOne(x.getKeywordId()) )
        .collect( Collectors.toList() );
        }

public KeywordCvPivot save( KeywordCvPivot obj) {
        return keywordCvDao.save(obj);
        }

public boolean deleteByCvId(int id) {
        return keywordCvDao.delete(id);
        }
}
