package org.framework.beans.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class InjectionExector {
    //创建一个迭代器用于存放injectinFactory的实现类
    private Iterator<InjectionFactory> it;
    public InjectionExector() {
        it = ServiceLoader.load(InjectionFactory.class).iterator();

    }
    //循环执行迭代器里的类
    public static void exector(BeanFactory factory, Class<?> clazz,Object bean){
        Iterator<InjectionFactory> it=new InjectionExector().it;
        while (it.hasNext()){
            it.next().injectionFactory(factory,clazz,bean);
        }
    }


}
