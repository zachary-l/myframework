package org.framework.beans.factory;


import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Scope;
import org.framework.beans.util.BeanNameUtil;
import org.framework.beans.util.ScanUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {

    //单例的容器
    private final Map<String, Object> singleton = new HashMap<>();
    //原型的容器
    private final Map<String, Definition> prototype = new HashMap<>();

    /**
     * 在构造方法中初始化并构建所有bean描述
     * 以及单例的bean
     *
     * 扫描路径
     */
    public BeanFactory(String pathName) {

        List<String> classList =  ScanUtil.scan(pathName);
        //初始化原型
        initPrototype(classList);
        //初始化单例
        initSingleton();
    }


    /**
     * 初始化原型工厂
     *
     *  pathName
     * @throws Exception
     */
    private void initPrototype(List<String> classList) {
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

    //put进原型容器
    private void setPrototype(Class<?> clazz) {
        //检查beanClass是否标注了@Component注解
        if (clazz.isAnnotationPresent(Component.class)) {
            //获取@Component注解的value属性的值，这个值作为bean在容器的唯一标识
            String beanName = createBeanName(clazz);
            //如果容器已经存在bean，则抛出异常
            if (prototype.containsKey(beanName)) {
                throw new RuntimeException(
                        "conflicts with existing, non-compatible bean definition of same name and class ["
                                + clazz + "]");
            }else{
                Definition df = setDefinition(clazz);
                prototype.put(clazz.getAnnotation(Component.class).value(), df);
            }
        }
    }
    //获取beanName
    private String createBeanName(Class<?> beanClass) {
        Component annotation = beanClass.getAnnotation(Component.class);
        //如果没有执行value，默认将类名作为beanName，并将类名首字母变为小写
        return ("".equals(annotation.value())) ? BeanNameUtil
                .toLowerBeanName(beanClass.getSimpleName()) : annotation
                .value();
    }

    //设置bean描述
    private Definition setDefinition(Class<?> clazz) {
        Definition definition = new Definition();
        definition.setId(clazz.getAnnotation(Component.class).value());
        definition.setClazz(clazz);
        definition.setScope(setDefScope(clazz));
        definition.setInitMethod(setInitMethod(clazz));
        definition.setDestroyMethod(setDestroyMethod(clazz));
        return definition;
    }
    //设置类型
    private static String setDefScope(Class<?> clazz){
        String scope = (clazz.isAnnotationPresent(Scope.class)) ? clazz
                .getAnnotation(Scope.class).value() : "singleton";
        return scope;
    }

    //判断初始化后是否有方法执行，如果有set进bean描述
    public Method setInitMethod(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        Method m = null;
        for (Method ms : methods) {
            if (ms.isAnnotationPresent(PostConstruct.class)) {
                m = ms;
            }
        }
        return m;
    }

    //判断销毁前执行的方法，set进bean描述中
    public Method setDestroyMethod(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        Method m = null;
        for (Method ms : methods) {
            if (ms.isAnnotationPresent(PreDestroy.class)) {
                m = ms;
            }
        }
        return m;
    }

    /**
     * 初始化单例的容器
     */
    private void initSingleton() {
        for (String key : prototype.keySet()) {
            Definition def = prototype.get(key);
            if ("singleton".equals(def.getScope())) {
                Object bean = createBean(def);
                singleton.put(key, bean);
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
     *
     * @param id
     * @return
     */
    private Object getContainerBean(String id) {
        Object bean = singleton.get(id);
        if (bean == null) {
            bean = getPrototype(id);
        }
        return bean;
    }

    /**
     * 获取原型实例
     *
     * @param id
     * @return
     */
    private Object getPrototype(String id) {
        //判断prototype集合中是否存在id键
        if (prototype.containsKey(id)) {
            return createBean(prototype.get(id));
        }
        throw new RuntimeException();
    }


    /**
     * 创建实例并执行注入
     *
     * @param
     * @return
     */
    private Object createBean(Definition definition) {
        try {
            Object instance = definition.getClazz().newInstance();
            //初始化执行init
            exectorInit(definition, instance);
            //执行注入
            setinject(instance, definition.getClazz());
            return instance;
        } catch (InstantiationException e) {
            throw new RuntimeException("Create bean fail.");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Create bean fail.");
        }
    }

    //初始化执行init
    private void exectorInit(Definition definition, Object instance) {
        Method method = definition.getInitMethod();
        try {
            if (method != null) {
                method.invoke(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //关闭工厂
    public void close() {
        executeDestroyMethods();
        singleton.clear();
        prototype.clear();
    }

    //销毁前执行
    private void executeDestroyMethods() {
        for (String p : prototype.keySet()) {
            Definition definition = prototype.get(p);
            Object s = singleton.get(p);
            if (s != null) {
                try {
                    definition.getDestroyMethod().invoke(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

        }
    }

    //通过迭代器执行方法和字段的注入
    private void setinject(Object bean, Class<?> clazz) {
        InjectionExector.exector(this, clazz, bean);
    }

}
