package org.framework.beans.factory.impl;

import org.framework.beans.annotation.Inject;
import org.framework.beans.factory.BeanFactory;
import org.framework.beans.factory.InjectionFactory;

import java.lang.reflect.Field;

/**
 * 通过字段执行注入
 */
public class FieldInjectionFactory implements InjectionFactory {
    @Override
    public void injectionFactory(BeanFactory factory, Class<?> clazz, Object bean) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Inject.class)) {
                Inject inject = f.getAnnotation(Inject.class);
                f.setAccessible(true);
                try {
                    f.set(bean, factory.getBean(inject.value(), f.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

