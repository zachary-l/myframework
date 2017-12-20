package org.framework.mvc.filter;

@org.framework.mvc.ann.MyFilter(value = "/*",order = 1)
public class Filter1 extends MyFilter {

    @Override
    public void before() {
        System.out.println("before");
    }

    @Override
    public void doFilter(HandlerFilterChain chain) {
        System.out.println("dofilter");
        chain.handle();
    }

    @Override
    public void after() {
        System.out.println("after");
    }
}
