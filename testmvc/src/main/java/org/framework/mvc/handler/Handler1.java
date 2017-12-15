package org.framework.mvc.handler;

import org.framework.mvc.ann.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Handler1 {
    @RequestMapping("/handler1")
    public void handler(HttpServletRequest request, HttpServletResponse response){
        try {
            response.getWriter().println("handler1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
