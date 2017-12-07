package org.framework.beans.factory;


import org.framework.beans.Component;
import org.framework.beans.Scope;
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
     * @param pathName
     * @throws Exception
     */
    private void initPrototype(String pathName){
        List<String> classList = ScanUtil.scan(pathName);
        for(String cl:classList){
            Class<?> clazz = null;
            try {
                clazz = Class.forName(cl);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(clazz.isAnnotationPresent(Component.class)){
                Definition df = setDefinition(clazz);
                prototype.put(clazz.getAnnotation(Component.class).value(),df);
            }
        }
    }
    private static Definition setDefinition(Class<?> clazz){
        Definition definition = new Definition();
        definition.setId(clazz.getAnnotation(Component.class).value());
        definition.setClazz(clazz);
        definition.setScope(clazz.getAnnotation(Scope.class).value());
        return definition;
    }

    /**
     * 初始化单例的容器
     */
    private void initSingleton(){
        for (String key : prototype.keySet()) {
            Definition def = prototype.get(key);
            if("singleton".equals(def.getScope())){
                try {
                    singleton.put(key,def.getClazz().newInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public <T> T getBean(String name){
        return (T)getContainerBean(name);
    }

    public <T> T getBean(String name, Class<T> clazz){
        return (T)getContainerBean(name);
    }

    private Object getContainerBean(String name){
        //获取作用域属性
        String scope = prototype.get(name).getScope();
        try {
            return ("singleton".equals(scope)) ? singleton.get(name) :
                    prototype.get(name).getClazz().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
