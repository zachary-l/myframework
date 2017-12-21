package org.framework.mvc.filter;

import org.framework.mvc.ActionContext;
import org.framework.mvc.ActionMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class MyFilter implements Interceptor {
    HttpServletRequest request = ActionContext.getContext().getRequest();
    HttpServletResponse response = ActionContext.getContext().getResponse();

    @Override
    public Object execute(HandlerFilterChain chain) {
        before();
        Object obj = doFilter(chain);
        after();
        return obj;
    }
     public abstract void before();
    public abstract Object doFilter(HandlerFilterChain chain);
    public abstract void after();
}
