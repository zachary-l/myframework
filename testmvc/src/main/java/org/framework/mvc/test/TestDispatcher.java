package org.framework.mvc.test;


import java.util.List;

import static org.framework.mvc.util.ScanUtil.scanPackage;

public class TestDispatcher {
    public void disTest(){
            int i = 0;
            List<String> classNames = scanPackage();
            for (String className : classNames) {
                i++;
                System.out.println(className);
            }
            System.out.println(i);
    }
}
