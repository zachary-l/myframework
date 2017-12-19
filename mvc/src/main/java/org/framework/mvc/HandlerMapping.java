package org.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class HandlerMapping {
    HttpServletRequest request = ActionContext.getContext().getRequest();
    public ActionMapper findHandler(){
        String urlPath = request.getServletPath();
        Map<String,HandlerDefinition> mappings = (Map<String,HandlerDefinition>)request.getServletContext().getAttribute(DispatcherServlet.DETINITIONS);
        ActionMapper actionMapper = new ActionMapper();
        actionMapper.setDefinition(mappings.get(urlPath));
        return actionMapper;
    }
}
