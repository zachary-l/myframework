package org.framework.mvc;

import java.lang.reflect.Method;
import java.util.List;

public class HandlerDefinition {
    private Class<?> clazz;
    private Method method;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

}
