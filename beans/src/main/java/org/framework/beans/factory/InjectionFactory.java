package org.framework.beans.factory;


public interface  InjectionFactory {
    void injectionFactory(BeanFactory factory, Class<?> clazz,Object bean);
}














/*
        if(clazz.isAnnotationPresent(Inject.class)){
        String f=clazz.getAnnotation(Inject.class).name();
        try {
        Object obj = clazz.newInstance();

        } catch (Exception e) {
        e.printStackTrace();
        }
        factory.getBean(f);
        }*/
