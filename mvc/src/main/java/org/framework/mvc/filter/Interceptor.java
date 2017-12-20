package org.framework.mvc.filter;

public interface Interceptor {
    void execute(HandlerFilterChain chain);
}
