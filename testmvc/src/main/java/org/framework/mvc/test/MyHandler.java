package org.framework.mvc.test;

/**
 * Created by Administrator on 2017/12/15.
 */
public class MyHandler {
    //testa?a=5&c=abc
    public  void testa(int a,String c){
        System.out.println("*****" +a);
        System.out.println("*****"+c);
    }

    public  void testb(Entity entity){
        System.out.println("-----" + entity);
    }
}
