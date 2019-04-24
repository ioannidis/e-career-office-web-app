package com.careeroffice.service.factory;

import com.careeroffice.service.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static Map<String, IPersistenceService<?>> serviceCache = new HashMap<>();

    public static IPersistenceService<?> getService(ServiceEnum serviceEnum) {

        if (serviceCache.get(serviceEnum.toString()) == null) {
            switch (serviceEnum) {
                case LoginService : {
                    serviceCache.put( serviceEnum.toString(), new LoginService() );
                    break;
                }
                case UserService: {
                    serviceCache.put( serviceEnum.toString(), new UserService() );
                    break;
                }
                case RegistrationService: {
                    serviceCache.put( serviceEnum.toString(), new RegistrationService() );
                    break;
                }
            }
        }
        return serviceCache.get(serviceEnum.toString());
    }
}
