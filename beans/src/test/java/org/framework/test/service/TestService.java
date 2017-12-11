package org.framework.test.service;

import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Inject;
import org.framework.beans.annotation.Scope;
import org.framework.test.dao.TestDao;

@Component("testService")
@Scope("prototype")
public class TestService {
    private TestDao testDao;
    @Inject("testDao")
    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    public void testService(){
        testDao.testDao();
    }
}
