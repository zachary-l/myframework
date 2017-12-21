package org.framework.mvc.filter;

import org.framework.mvc.ann.MyFilterAnn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyFilterAnn(value = "/*",order = 1)
public class Filter1 extends MyFilter {

    @Override
    public void before() {
        System.out.println("before1");
    }

    @Override
    public Object doFilter(HandlerFilterChain chain) {
        System.out.println("dofilter1");
        return chain.handle();
    }

    @Override
    public void after() {
        System.out.println("after1");
    }
}
