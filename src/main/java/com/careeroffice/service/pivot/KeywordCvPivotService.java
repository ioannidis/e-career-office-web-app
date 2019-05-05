package com.careeroffice.service.pivot;

import com.careeroffice.dao.KeywordDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.pivot.KeywordClassifiedPivotDao;
import com.careeroffice.dao.pivot.KeywordCvPivotDao;
import com.careeroffice.model.Keyword;
import com.careeroffice.model.KeywordClassifiedPivot;
import com.careeroffice.model.KeywordCvPivot;
import com.careeroffice.service.IPersistenceService;

import java.util.List;
import java.util.stream.Collectors;

public class KeywordCvPivotService implements IPersistenceService<KeywordCvPivot> {

    KeywordDao keywordDao = (KeywordDao) DaoFactory.getDao( DaoEnum.KeywordDao );
    KeywordCvPivotDao keywordCvPivotDao = (KeywordCvPivotDao) DaoFactory.getDao( DaoEnum.KeywordCvPivotDao );

    public List<Keyword> findByCvId(int id) {
        return keywordCvPivotDao.findByCvId(id).stream()
                .map( x -> keywordDao.findOne(x.getKeywordId()) )
                .collect( Collectors.toList() );
    }

    public KeywordCvPivot save(KeywordCvPivot obj) {
        return keywordCvPivotDao.save(obj);
    }

    public boolean deleteByClassifiedId(int id) {
        return keywordCvPivotDao.delete(id);
    }
}
