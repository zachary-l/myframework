package org.framework.mvc.interceptor;

/**
 * Created by Administrator on 2017/12/19.
 */
public class Interceptor3 implements Interceptor {
    @Override
    public boolean before() {
        System.out.println("拦截器333 before");
        return true;
    }

    @Override
    public void after() {
        System.out.println("拦截器3333 after");
    }
}

