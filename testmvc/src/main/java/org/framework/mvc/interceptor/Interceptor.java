package org.framework.mvc.interceptor;

/**
 * Created by Administrator on 2017/12/19.
 */
public interface Interceptor {
    boolean before();
    void after();
}
