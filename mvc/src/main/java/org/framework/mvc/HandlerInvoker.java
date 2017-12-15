package org.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerInvoker {
    public void handlerInvoker(HandlerDefinition definition, HttpServletRequest req, HttpServletResponse resp){
        Class<?> c = definition.getClazz();
        if(c!=null){
            try {
                Object instance = c.newInstance();
                definition.getMethod().invoke(instance,req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
