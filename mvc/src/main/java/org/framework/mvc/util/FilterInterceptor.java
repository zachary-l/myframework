package org.framework.mvc.util;

import org.framework.mvc.HandlerDefinition;
import org.framework.mvc.ann.MyFilterAnn;
import org.framework.mvc.filter.Interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回每个请求对应的list的过滤链
 */
public class FilterInterceptor {
    private Map<String,List<Interceptor>> interceptor = null;
    public Map<String,List<Interceptor>> interceptor(List<String> classNames){
        Map<String,HandlerDefinition> mappings =HandlerDefinitionParser.parser(classNames);
        //过滤器排序，
//        List<String> filterName=sortFilter(classNames);
        interceptor = new HashMap<>();
        for(String s:mappings.keySet()) {
            List<Interceptor> list = setServletMap(classNames,s);
            interceptor.put(s, list);
        }
        return interceptor;
    }

    //将注解的类拦截
    private List<Interceptor> setServletMap(List<String> classNames,String s){
        List<Interceptor> list = new ArrayList<>();
        for (String className : classNames) {
            Class<?> clazz = createClazz(className);
            String urlName = "";
            if (clazz.isAnnotationPresent(MyFilterAnn.class)) {
                MyFilterAnn servletUrl = clazz.getAnnotation(MyFilterAnn.class);
                if (servletUrl != null) {
                    urlName = servletUrl.value();
//                    System.out.println(urlName+"---"+ FilterUrlUtil.match(s,urlName));
                    if (FilterUrlUtil.match(s,urlName)) {
                        list.add((Interceptor) createInstance(clazz));
                    }
                }
            }
        }
        return list;
    }
    //过滤器排序---未完整
    private List<String> sortFilter(List<String> list){
        for (String className : list) {
            Class<?> clazz = createClazz(className);
            if (clazz.isAnnotationPresent(MyFilterAnn.class)) {
                MyFilterAnn servletUrl = clazz.getAnnotation(MyFilterAnn.class);
                int order = servletUrl.order();
                //。。。。。
            }
        }
        return list;
    }
    //创建实例
    private Object createInstance(Class<?> clazz){
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }
    //create Class<?> 文件对象
    private Class<?> createClazz(String className){
        Class<?> clazz = null;
        try {
            clazz= Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

}
