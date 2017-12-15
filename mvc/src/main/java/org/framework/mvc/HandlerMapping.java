package org.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HandlerMapping {
    public HandlerDefinition findHandler(HttpServletRequest request, HttpServletResponse response){
        String urlPath = request.getServletPath();
        Map<String,HandlerDefinition> mappings = (Map<String,HandlerDefinition>)request.getServletContext().getAttribute(DispatcherServlet.DETINITIONS);
        return mappings.get(urlPath);
    }
}
