package org.framework.test.controller;

import org.framework.beans.factory.BeanFactory;
import org.framework.test.service.TestService;

public class TestController {
    public void controller(){
        BeanFactory factory = new BeanFactory("org.framework.test.service");
        TestService testService= factory.getBean("TestService",TestService.class);
        testService.testService();
    }

    public static void main(String[] args){
        new TestController().controller();
    }
}
