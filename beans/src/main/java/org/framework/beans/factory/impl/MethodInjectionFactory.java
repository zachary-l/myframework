package org.framework.beans.factory.impl;

import org.framework.beans.annotation.Inject;
import org.framework.beans.factory.BeanFactory;
import org.framework.beans.factory.InjectionFactory;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过set方法执行注入
 */
public class    MethodInjectionFactory implements InjectionFactory {
    @Override
    public void injectionFactory(BeanFactory factory, Class<?> clazz, Object bean){
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        //通过BeanInfo获取整个Bean的属性描述信息
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        //循环遍历,解析每一个属性信息描述
        for (PropertyDescriptor pd : pds) {
            //获得字段对应的set方法
            Method method = pd.getWriteMethod();
            //判断方法上是否定义注解和是否为空
            if(method.isAnnotationPresent(Inject.class)&&method!=null){
                //判断方法是否是一个标准的set方法
                boolean b = isSetMet(pd,method);
                    if(b){
                    Inject inject = method.getAnnotation(Inject.class);
                    try {
                        method.invoke(bean,factory.getBean(inject.value(),method.getParameterTypes()[0]));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //是否是一个标准的set方法
    private boolean isSetMet(PropertyDescriptor pd,Method method){
        String firstChar = pd.getName().substring(0, 1);
        String setMethod = "set"
                + pd.getName().replaceFirst(firstChar,
                firstChar.toUpperCase());
        if (method.getName().equals(setMethod)){
            return true;
        }else{
            throw new RuntimeException();
        }
    }
}
