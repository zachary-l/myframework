package org.framework.test;

import org.framework.test.dao.TestDao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class demo1 {
    public static void main(String[] args) throws Exception{
        //通过内省来操作JavaBean，返回一个BeanInfo对象，这个对象描述整个Bean实例的信息
        //包括字段信息，以及get和set方法信息,并排除所有引用类型字段继承自Object的属性信息
        BeanInfo beanInfo = Introspector.getBeanInfo(TestDao.class, Object.class);
        //通过BeanInfo获取整个Bean的属性描述信息
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        //循环遍历,解析每一个属性信息描述
            for (PropertyDescriptor pd : pds) {
            //获取字段的名称
            System.out.println(pd.getName());
            //获取字段对应的set方法
            Method setMethod = pd.getWriteMethod();
            System.out.println(setMethod.getName());
            //获取字段对应的get方法
            Method getMethod = pd.getReadMethod();
            System.out.println(getMethod.getName());
            System.out.println("-------------------------");
        }
    }
}
