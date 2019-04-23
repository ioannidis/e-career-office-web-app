package com.careeroffice.service.factory;

import com.careeroffice.service.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static Map<String, IPersistenceService<?>> serviceCache = new HashMap<>();

    public static IPersistenceService<?> getService(ServiceEnum serviceEnum, DataSource ds) {

        if (serviceCache.get(serviceEnum.toString()) == null) {
            switch (serviceEnum) {
                case LoginService : {
                    serviceCache.put( serviceEnum.toString(), new LoginService(ds) );
                    break;
                }
                case UserService: {
                    serviceCache.put( serviceEnum.toString(), new UserService( ds ) );
                    break;
                }
                case RegistrationService: {
                    serviceCache.put( serviceEnum.toString(), new RegistrationService( ds ) );
                    break;
                }
            }
        }
        return serviceCache.get(serviceEnum.toString());
    }
}
