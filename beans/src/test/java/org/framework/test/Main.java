package org.framework.test;

import org.framework.beans.factory.BeanFactory;
import org.framework.test.controller.TestController;
import org.framework.test.service.TestService;

public class Main {
    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory("org.framework.test");
        TestController testController= factory.getBean("testController",TestController.class);
        testController.controller();
        factory.close();
    }
}
