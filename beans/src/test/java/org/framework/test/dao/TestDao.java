package org.framework.test.dao;

import org.framework.beans.annotation.Component;
import org.framework.beans.annotation.Scope;

@Component("testDao")
@Scope("prototype")
public class TestDao {
    public void testDao(){
        System.out.println("dao");
    }
}
