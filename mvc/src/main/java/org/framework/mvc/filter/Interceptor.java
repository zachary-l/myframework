package org.framework.mvc.filter;

public interface Interceptor {
    Object execute(HandlerFilterChain chain);
}
