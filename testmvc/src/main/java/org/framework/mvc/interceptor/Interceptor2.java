package org.framework.mvc.interceptor;

/**
 * Created by Administrator on 2017/12/19.
 */
public class Interceptor2 implements Interceptor {
    @Override
    public boolean before() {
        System.out.println("拦截器222 before");
        return  false;
    }

    @Override
    public void after() {
        System.out.println("拦截器222 after");
    }
}