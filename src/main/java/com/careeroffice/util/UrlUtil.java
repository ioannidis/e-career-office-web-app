package com.careeroffice.util;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

    /**
     * Returns a default value if the parameter on the request is null.
     *
     * @param request The current request.
     * @param name The name of the parameter to fetch.
     * @param def The default value to return if the parameter is null.
     * @return A default value or the parameter.
     */
    public static String  getParameterOrDefault( HttpServletRequest request, String name, String def) {
        return request.getParameter(name) == null ? def : request.getParameter(name);
    }
}
