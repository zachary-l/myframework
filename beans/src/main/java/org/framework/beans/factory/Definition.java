package org.framework.beans.factory;

/**
 * 这里类是bean的描述定义，封装了配置文件中的bean配置信息
 */
public class Definition {
    //bean的唯一标识
    private String id;
    //bean的Class文件对象
    private Class<?> clazz;
    //bean的创建方式
    private String scope;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
