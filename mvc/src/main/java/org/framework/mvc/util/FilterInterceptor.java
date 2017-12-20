package org.framework.mvc.util;

import org.framework.mvc.HandlerDefinition;
import org.framework.mvc.ann.MyFilter;
import org.framework.mvc.filter.Interceptor;
import org.framework.mvc.util.FilterMatchUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterInterceptor {
    private Map<String,List<Interceptor>> interceptor = null;
    public Map<String,List<Interceptor>> interceptor(Map<String,HandlerDefinition> mappings,List<String> classNames){
//        List<String> classNames = scanPackage();
        System.out.println(classNames.size());
        interceptor = new HashMap<>();
        System.out.println("初始化过滤链");

        for(String s:mappings.keySet()) {
            List<Interceptor> list = setServletMap(classNames,s);
            System.out.println("filter:-"+s+"---"+list);
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
            if (clazz.isAnnotationPresent(MyFilter.class)) {
                MyFilter servletUrl = clazz.getAnnotation(MyFilter.class);
                if (servletUrl != null) {
                    urlName = servletUrl.value();
                    System.out.println(urlName+"---"+ FilterMatchUtil.match(s,urlName));
                    if (FilterMatchUtil.match(s,urlName)) {
                        list.add((Interceptor) createInstance(clazz));
                    }
                }
            }
        }
        return list;
    }
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
