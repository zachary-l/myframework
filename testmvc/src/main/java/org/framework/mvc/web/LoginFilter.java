package org.framework.mvc.web;

import org.framework.mvc.ann.MyFilterAnn;
import org.framework.mvc.filter.HandlerFilterChain;
import org.framework.mvc.filter.MyFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@MyFilterAnn(value = "/display",order = 1)
public class LoginFilter extends MyFilter {
    @Override
    public void before() {

    }

    @Override
    public Object doFilter(HandlerFilterChain chain) {
            return "登陆失败";
    }

    @Override
    public void after() {

    }
}
