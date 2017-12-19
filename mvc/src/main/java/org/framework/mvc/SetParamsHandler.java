package org.framework.mvc;

import org.framework.mvc.paramsHandler.HandlerChain;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;

public abstract class SetParamsHandler {
    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) ActionContext.getContext().getRequest();

    }
    public abstract Object handle(Parameter parameter, HandlerChain chain);
}
