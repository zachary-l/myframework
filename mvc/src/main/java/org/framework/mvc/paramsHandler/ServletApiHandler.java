package org.framework.mvc.paramsHandler;

import org.framework.mvc.ActionContext;
import org.framework.mvc.SetParamsHandler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Parameter;

public class ServletApiHandler extends SetParamsHandler {
    @Override
    public Object handle(Parameter parameter, HandlerChain chain) {
        if(parameter.getType().equals(HttpServletResponse.class)){
            return ActionContext.getContext().getResponse();
        }else if(parameter.getType().equals(HttpServletRequest.class)){
            return getRequest();
        } else if (parameter.getType().equals(HttpSession.class)) {
            return getRequest().getSession();
        } else if (parameter.getType().equals(ServletContext.class)) {
            return getRequest().getServletContext();
        }
        return chain.execute(parameter);
    }
}
