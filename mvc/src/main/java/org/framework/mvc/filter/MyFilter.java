package org.framework.mvc.filter;

public abstract class MyFilter implements Interceptor {

    @Override
    public void execute(HandlerFilterChain chain) {
        before();
        doFilter(chain);
        after();

    }
     public abstract void before();
    public abstract void doFilter(HandlerFilterChain chain);
    public abstract void after();
}
