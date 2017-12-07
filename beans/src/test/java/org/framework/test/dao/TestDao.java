package org.framework.test.dao;

import org.framework.beans.Component;
import org.framework.beans.Scope;

@Component("TestDao")
@Scope("singleton")
public class TestDao {
    public void testDao(){
        System.out.println("dao");
    }
}
