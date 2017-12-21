package org.framework.mvc.filter;

import org.framework.mvc.ann.MyFilterAnn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyFilterAnn(value = "/handlers/method2",order = 2)
public class Filter3 extends MyFilter {
    @Override
    public void before() {
        System.out.println("before3");
    }

    @Override
    public Object doFilter(HandlerFilterChain chain) {
        System.out.println("dofilter3 不放行");
        return null;
    }

    @Override
    public void after() {
        System.out.println("after3");
    }
}
