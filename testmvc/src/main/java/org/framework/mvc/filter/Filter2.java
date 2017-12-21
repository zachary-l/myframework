package org.framework.mvc.filter;

import org.framework.mvc.ann.MyFilterAnn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyFilterAnn(value = "/handlers/method1",order = 2)
public class Filter2 extends MyFilter {
    @Override
    public void before() {
        System.out.println("before2");
    }

    @Override
    public Object doFilter(HandlerFilterChain chain) {
        System.out.println("dofilter2");
        return chain.handle();
    }

    @Override
    public void after() {
        System.out.println("after2");
    }
}