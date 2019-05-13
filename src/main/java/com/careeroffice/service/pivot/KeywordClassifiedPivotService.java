package com.careeroffice.service.pivot;

import com.careeroffice.dao.KeywordDao;
import com.careeroffice.dao.factory.DaoEnum;
import com.careeroffice.dao.factory.DaoFactory;
import com.careeroffice.dao.pivot.KeywordClassifiedPivotDao;
import com.careeroffice.model.Keyword;
import com.careeroffice.model.KeywordClassifiedPivot;
import com.careeroffice.service.IPersistenceService;

import java.util.List;
import java.util.stream.Collectors;

public class KeywordClassifiedPivotService implements IPersistenceService<KeywordClassifiedPivot> {

    private KeywordDao keywordDao = (KeywordDao) DaoFactory.getDao(DaoEnum.KeywordDao);
    private KeywordClassifiedPivotDao keywordClassifiedPivotDao = (KeywordClassifiedPivotDao) DaoFactory.getDao(DaoEnum.KeywordClassifiedPivotDao);

    public List<Keyword> findByClassified(int id) {
        return keywordClassifiedPivotDao.findByClassified(id).stream()
                .map(x -> keywordDao.findOne(x.getKeywordId()))
                .collect(Collectors.toList());
    }

    public KeywordClassifiedPivot save(KeywordClassifiedPivot obj) {
        return keywordClassifiedPivotDao.save(obj);
    }

    public boolean deleteByClassifiedId(int id) {
        return keywordClassifiedPivotDao.delete(id);
    }
}
