package com.careeroffice.dao.factory;

import com.careeroffice.dao.*;
import com.careeroffice.dao.pivot.KeywordClassifiedPivotDao;
import com.careeroffice.dao.pivot.KeywordCvPivotDao;
import com.careeroffice.dao.CvDao;
import com.careeroffice.dao.student.KeywordCvDao;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private static Map<String, CrudDao> dao = new HashMap<>();

    public static CrudDao getDao(DaoEnum daoEnum) {

        if (dao.get(daoEnum.toString()) == null) {
            switch (daoEnum) {
                case UserDao: {
                    dao.put(daoEnum.toString(), new UserDao() );
                    break;
                }
                case ClassifiedDao: {
                    dao.put(daoEnum.toString(), new ClassifiedDao() );
                    break;
                }
                case RoleDao: {
                    dao.put(daoEnum.toString(), new RoleDao());
                    break;
                }
                case CompanyDao: {
                    dao.put(daoEnum.toString(), new CompanyDao());
                    break;
                }
                case DepartmentDao: {
                    dao.put(daoEnum.toString(), new DepartmentDao());
                    break;
                }
                case CategoryDao: {
                    dao.put( daoEnum.toString(), new CategoryDao());
                    break;
                }
                case CvDao: {
                    dao.put(daoEnum.toString(), new CvDao());
                    break;
                }
                case UserCompanyDao: {
                    dao.put( daoEnum.toString(), new UserCompanyDao() );
                    break;
                }
                case UserDepartmentDao: {
                    dao.put( daoEnum.toString(), new UserDepartmentDao() );
                    break;
                }
                case KeywordDao: {
                    dao.put( daoEnum.toString(), new KeywordDao() );
                    break;
                }
                case KeywordCvDao: {
                    dao.put( daoEnum.toString(), new KeywordCvDao() );
                    break;
                }
                case KeywordClassifiedPivotDao: {
                    dao.put( daoEnum.toString(), new KeywordClassifiedPivotDao() );
                    break;
                }
                case KeywordCvPivotDao: {
                    dao.put( daoEnum.toString(), new KeywordCvPivotDao() );
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
