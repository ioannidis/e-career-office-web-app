package com.careeroffice.service.factory;

import com.careeroffice.service.*;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static Map<String, IPersistenceService<?>> serviceCache = new HashMap<>();

    public static IPersistenceService<?> getService(ServiceEnum serviceEnum) {

        if (serviceCache.get(serviceEnum.toString()) == null) {
            switch (serviceEnum) {
                case LoginService: {
                    serviceCache.put(serviceEnum.toString(), new LoginService());
                    break;
                }
                case UserService: {
                    serviceCache.put(serviceEnum.toString(), new UserService());
                    break;
                }
                case RegistrationService: {
                    serviceCache.put(serviceEnum.toString(), new RegistrationService());
                    break;
                }
                case RoleService: {
                    serviceCache.put(serviceEnum.toString(), new RoleService());
                    break;
                }
                case CompanyService: {
                    serviceCache.put(serviceEnum.toString(), new CompanyService());
                    break;
                }
                case DepartmentService: {
                    serviceCache.put(serviceEnum.toString(), new DepartmentService());
                    break;
                }
                case ClassifiedService: {
                    serviceCache.put( serviceEnum.toString(), new ClassifiedService() );
                    break;
                }
                case CategoryService: {
                    serviceCache.put( serviceEnum.toString(), new CategoryService() );
                    break;
                }
                case UserCompanyService: {
                    serviceCache.put( serviceEnum.toString(), new UserCompanyService() );
                    break;

                }
                case UserDepartmentService: {
                    serviceCache.put( serviceEnum.toString(), new UserDepartmentService() );
                    break;

                }
                default:
                    // TODO: Throw exception
                    break;
            }
        }
        return serviceCache.get(serviceEnum.toString());
    }
}
