package org.framework.mvc.filter;

import org.framework.mvc.ann.MyFilterAnn;

@MyFilterAnn(value = "/handleF",order = 1)
public class Filter4 extends MyFilter {
    @Override
    public void before() {
        System.out.println("before4");
    }

    @Override
    public Object doFilter(HandlerFilterChain chain) {
        System.out.println("doFilter4");
        return "不放行";
    }

    @Override
    public void after() {
        System.out.println("after4");
    }
}
