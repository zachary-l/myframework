package org.framework.beans.factory;


import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Scope;
import org.framework.beans.util.ScanUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {
    public BeanFactory(String pathName) {
        initPrototype(pathName);
        initSingleton();
    }

    //单例的容器
    private static Map<String, Object> singleton = new HashMap<String, Object>();
    //原型的容器
    private static Map<String, Definition> prototype = new HashMap<String, Definition>();

    /**
     * 初始化原型工厂
     *
     * @param pathName
     * @throws Exception
     */
    private void initPrototype(String pathName) {
        List<String> classList = ScanUtil.scan(pathName);
        for (String cl : classList) {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(cl);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            setPrototype(clazz);
        }
    }

    private static void setPrototype(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Component.class)) {
            Definition df = setDefinition(clazz);
            prototype.put(clazz.getAnnotation(Component.class).value(), df);
        }
    }

    private static Definition setDefinition(Class<?> clazz) {
        Definition definition = new Definition();
        definition.setId(clazz.getAnnotation(Component.class).value());
        definition.setClazz(clazz);
        definition.setScope(clazz.getAnnotation(Scope.class).value());
        return definition;
    }

    /**
     * 初始化单例的容器
     */
    private void initSingleton() {
        for (String key : prototype.keySet()) {
            Definition def = prototype.get(key);
            if ("singleton".equals(def.getScope())) {
                try {
                    singleton.put(key, createBean(def.getClazz()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public <T> T getBean(String name) {
        return (T) getContainerBean(name);
    }

    public <T> T getBean(String name, Class<T> clazz) {
        return (T) getContainerBean(name);
    }
    /**
     * 根据id获取实例对象
     * @param id
     * @return
     */
    private Object getContainerBean(String id){
        Object bean = singleton.get(id);
        if(bean == null){
            bean = getPrototype(id);
        }
        return bean;
    }

    /**
     * 获取原型实例
     * @param id
     * @return
     */
    private Object getPrototype(String id){
        if(prototype.containsKey(id)){
            return createBean(prototype.get(id).getClazz());
        }
        throw new RuntimeException();
    }


    /**
     *  创建实例并执行注入
     * @param clazz
     * @return
     */
    private Object createBean(Class<?> clazz){
        try {
            Object instance = clazz.newInstance();
            //执行注入
            setinject(instance, clazz);
            return instance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Create bean fail.");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Create bean fail.");
        }
    }

    private void setinject(Object bean, Class<?> clazz){
        InjectionFactory inf = new FieldInjectionFactory();
        InjectionFactory inf1 = new MethodInjectionFactory();
        try {
            inf1.injectionFactory(this,clazz,bean);
//            inf.injectionFactory(this, clazz, bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
