package org.framework.mvc.test;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/15.
 */
public class Test {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Map<String, Object> maps = new HashMap<>();
        maps.put("a",5);
        maps.put("b", 100);
        maps.put("c", "abc");

        MyHandler handler = new MyHandler();


        Method[] methods = handler.getClass().getDeclaredMethods();


        for(Method method1: methods){
            if(method1.getName().equals("testa")){
                Object[] values = null;
                int count = method1.getParameterCount();
                 values = new Object[count];
                for(int i=0;i<count;i++){
                    Parameter parameter = method1.getParameters()[i];
                    System.out.println("---name: " + parameter.getName());
                    System.out.println("---type:" + parameter.getType());
                    values[i] = ConvertUtils.convert(maps.get(parameter.getName()), parameter.getType());
                    System.out.println(i+"-->"+values[i]);
                }

                method1.invoke(handler, values);
            }else if(method1.getName().equals("testb")) {
                Parameter parameter = method1.getParameters()[0];
                Object paramObject = parameter.getType().newInstance();
                System.out.println(paramObject.toString());
                BeanUtils.populate(paramObject,maps);
                method1.invoke(handler, paramObject);
            }
        }

    }


}
