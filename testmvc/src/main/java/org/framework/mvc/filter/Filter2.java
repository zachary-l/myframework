package org.framework.mvc.filter;

@org.framework.mvc.ann.MyFilter(value = "/handlers/method1",order = 2)
public class Filter2 extends MyFilter {
    @Override
    public void before() {
        System.out.println("before2");
    }

    @Override
    public void doFilter(HandlerFilterChain chain) {
        System.out.println("dofilter2");
        chain.handle();
    }

    @Override
    public void after() {
        System.out.println("after2");
    }
}
