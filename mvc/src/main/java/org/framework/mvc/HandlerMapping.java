package org.framework.mvc;

import org.framework.mvc.filter.HandlerFilterChain;
import org.framework.mvc.filter.Interceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class HandlerMapping {
    HttpServletRequest request = ActionContext.getContext().getRequest();
    public HandlerFilterChain findHandler(){
        String urlPath = request.getServletPath();
        Map<String,HandlerDefinition> mappings = (Map<String,HandlerDefinition>)request.getServletContext().getAttribute(DispatcherServlet.DETINITIONS);
        ActionMapper actionMapper = new ActionMapper();
        actionMapper.setDefinition(mappings.get(urlPath));
        Map<String,List<Interceptor>> listMap = (Map<String,List<Interceptor>>)request.getServletContext().getAttribute(DispatcherServlet.FILTERDETINITIONS);
        List<Interceptor> list = listMap.get(urlPath);
        actionMapper.setFilterChainList(list);
        HandlerFilterChain filterChain=null;
        filterChain = new HandlerFilterChain(actionMapper);
        return filterChain;
    }
}
