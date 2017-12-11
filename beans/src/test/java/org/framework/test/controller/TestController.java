package org.framework.test.controller;

import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.test.service.TestService;

@Component("testController")
@Scope("prototype")
public class TestController {

    private TestService testService;
    @Inject("testService")
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public void controller() {
    testService.testService();
    }
}
