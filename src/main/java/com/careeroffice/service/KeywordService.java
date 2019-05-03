package com.careeroffice.service;

import com.careeroffice.dao.KeywordClassifiedPivotDao;
import com.careeroffice.dao.KeywordDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.model.Keyword;
import com.careeroffice.model.KeywordClassifiedPivot;

import java.util.List;
import java.util.stream.Collectors;

public class KeywordService implements IPersistenceService<Keyword> {

    KeywordDao keywordDao = (KeywordDao) DaoFactory.getDao( DaoEnum.KeywordDao );
    KeywordClassifiedPivotDao keywordClassifiedPivotDao = (KeywordClassifiedPivotDao) DaoFactory.getDao( DaoEnum.KeywordClassifiedPivotDao );

    public Keyword findOne(int id) {
        return keywordDao.findOne(id);
    }

    public List<Keyword> findAll() {
        return keywordDao.findAll();
    }

    public List<Keyword> findByClassified(int id) {
        return keywordClassifiedPivotDao.findByClassified(id).stream()
                .map( x -> keywordDao.findOne(x.getKeywordId()) )
                .collect( Collectors.toList() );
    }
}
