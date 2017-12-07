package org.framework.test.service;

import org.framework.beans.Component;
import org.framework.beans.Scope;
import org.framework.beans.factory.BeanFactory;
import org.framework.test.dao.TestDao;

@Component("TestService")
@Scope("singleton")
public class TestService {
    public void testService(){
        BeanFactory factory = new BeanFactory("org.framework.test.dao");
        TestDao testDao= factory.getBean("TestDao",TestDao.class);
        testDao.testDao();
    }
}
