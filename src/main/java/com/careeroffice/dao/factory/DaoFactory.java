package com.careeroffice.dao.factory;

import com.careeroffice.dao.CrudDao;
import com.careeroffice.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private static Map<String, CrudDao> dao = new HashMap<>();

    public static CrudDao getDao( DaoEnum daoEnum) {

        if (dao.get(daoEnum.toString()) == null) {
            switch (daoEnum) {
                case UserDao: {
                    dao.put( daoEnum.toString(), new UserDao() );
                    break;
                }
                default:
                    //TODO: Throw exception
                    return null;
            }
        }
        return dao.get(daoEnum.toString());
    }
}
