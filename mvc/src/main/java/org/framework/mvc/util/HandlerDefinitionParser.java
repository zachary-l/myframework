package org.framework.mvc.util;

import org.framework.mvc.HandlerDefinition;
import org.framework.mvc.ann.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.framework.mvc.util.ScanUtil.scanPackage;

/**
 * 扫描项目下的所有类
 * 判断类、方法上是否定义了requestMapping注解
 * return Map<String,HandlerDefinition>
 */
public class HandlerDefinitionParser {
    private static Map<String,HandlerDefinition> mappings = null;
    public static Map<String,HandlerDefinition> parser(List<String> classNames){
//        List<String> classNames = scanPackage();
         mappings = new HashMap<>();
        for (String className : classNames) {
            Class<?> clazz = createClazz(className);
            setServletMap(clazz);
        }
       return mappings;
    }
    //create Class<?> 文件对象
    private static Class<?> createClazz(String className){
        Class<?> clazz = null;
        try {
            clazz= Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
    //将注解的类拦截,//类上没定义注解，方法上定义注解
    private static void setServletMap(Class<?> clazz){
        String urlClassName = isServletUrlClass(clazz);
        Method[] methods = clazz.getMethods();
        //获取方法
        for(Method m:methods){
            isServletUrlMethod(clazz,m,urlClassName);
        }
    }
    //判断clzz是否是一个servletUrl注解类，并返回注解名字
    private static String isServletUrlClass(Class<?> clazz){
        String urlName = "";
        //判断是否是注解类
        if(clazz.isAnnotationPresent(RequestMapping.class)){
            RequestMapping servletUrl = clazz.getAnnotation(RequestMapping.class);
            if(servletUrl!=null){
                urlName = servletUrl.value();
            }
        }
        return urlName;
    }
    //判断方法是否是一个请求方法
    private static String isServletUrlMethod(Class<?> clazz,Method m,String urlClassName){
        String urlName = "";
        if(m.isAnnotationPresent(RequestMapping.class)){
            urlName = m.getAnnotation(RequestMapping.class).value();
            HandlerDefinition definition = setDefinition(clazz,m);
            /*System.out.println(urlClassName+urlName);
            System.out.println(definition);*/
            mappings.put(urlClassName+urlName,definition);
        }
        return urlName;
    }
    //设置类描述
    private static HandlerDefinition setDefinition(Class<?> clazz, Method m){
        HandlerDefinition definition = new HandlerDefinition();
        definition.setClazz(clazz);
        definition.setMethod(m);
        return definition;
    }

}
