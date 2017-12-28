package org.framework.test.controller;

import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.test.service.TestService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("testController")
public class TestController {

    private TestService testService;
    @Inject("testService")
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public void controller() {
    testService.testService();
    }
    @PostConstruct
    public void beanInit(){
        System.out.println("beanInit初始化");
    }
    @PreDestroy
    public void beanDestroy(){
        System.out.println("beanDestroy");
    }
}
