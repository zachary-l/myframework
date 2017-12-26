package org.framework.plugin;

import org.framework.beans.annotation.Component;
import org.framework.beans.factory.BeanFactory;
import org.framework.beans.util.BeanNameUtil;
import org.framework.mvc.HandlerDefinition;
import org.framework.mvc.HandlerFactory;

import java.lang.reflect.Method;

public class ContextFactory implements HandlerFactory {
    private BeanFactory beanFactory;

    public ContextFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public Object createHandler(HandlerDefinition definition) {
        String beanName =getBeanName(definition.getClazz());
        Object handler = beanFactory.getBean(beanName,definition.getClazz());
        return handler;
    }
    private String getBeanName(Class<?> clazz){
        if(clazz.getAnnotation(Component.class)==null){
            throw new RuntimeException();
        }
        String beanName =clazz.getAnnotation(Component.class).value();
        beanName = ("".equals(beanName)) ? BeanNameUtil.toLowerBeanName((clazz.getSimpleName())) : beanName;
        return beanName;
    }


}
